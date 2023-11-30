import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatChipEditedEvent, MatChipInputEvent, MatChipsModule} from '@angular/material/chips';
import { LiveAnnouncer} from '@angular/cdk/a11y';
import { COMMA, ENTER} from '@angular/cdk/keycodes';
import { ActivatedRoute } from '@angular/router';
import { VideoService } from '../services/video.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { VideoDto } from '../model/video-dto';

@Component({
  selector: 'app-save-video-details',
  templateUrl: './save-video-details.component.html',
  styleUrls: ['./save-video-details.component.scss']
})
export class SaveVideoDetailsComponent implements OnInit {

  saveVideoForm! : FormGroup;
  addOnBlur = true;
  readonly separatorKeysCodes =  [ENTER, COMMA] as const;
  tags: string[] = [];
  selectedFile!: File;
  selectedFileName= '';
  videoId= '';
  fileSelected: boolean= false;
  videoUrl: string= '';
  thumbnailUrl: string= '';

  announcer = inject(LiveAnnouncer);

  constructor(public formBuilder: FormBuilder,
              private activateRoute: ActivatedRoute,
              private videoService: VideoService,
              private snackBar: MatSnackBar) {

                this.videoId= this.activateRoute.snapshot.params['videoId'];
                this.videoService.getDetailsVideo(this.videoId).subscribe({
                  next: video=> {
                    console.log("video id "+this.videoId)
                    this.videoUrl= video.videoUrl;
                    this.thumbnailUrl= video.thumbnailUrl;
                  },
                  error: err=> {
                    console.log("error to get video "+err);
                  }
                })
          
  }
  ngOnInit(): void {
    this.saveVideoForm= this.formBuilder.group({
      title: this.formBuilder.control('', Validators.required),
      description: this.formBuilder.control(''),
      videoStatus: this.formBuilder.control('private')
    })
  }

  add(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();

    // Add our fruit
    if (value) {
      this.tags.push(value);
    }

    // Clear the input value
    event.chipInput!.clear();
  }

  remove(tag: string): void {
    const index = this.tags.indexOf(tag);

    if (index >= 0) {
      this.tags.splice(index, 1);

      this.announcer.announce(`Removed ${tag}`);
    }
  }

  edit(tag: string, event: MatChipEditedEvent) {
    const value = event.value.trim();

    // Remove fruit if it no longer has a name
    if (!value) {
      this.remove(tag);
      return;
    }

    // Edit existing fruit
    const index = this.tags.indexOf(tag);
    if (index >= 0) {
      this.tags[index] = value;
    }
  }

  saveVideoDetails() {
    const videoMetaData: VideoDto= this.saveVideoForm.value;
    videoMetaData.id= this.videoId;
    videoMetaData.tags= this.tags;

    this.videoService.saveVideo(videoMetaData).subscribe({
      next: data=> {
        this.snackBar.open("video details saved!", "OK");
      }, 
      error: err=> {
        console.log(err);
      }
    });
    
  }
  onFileSelected(event: Event) { 
    const target= event.target as HTMLInputElement;
    if (target.files && target.files.length> 0) {
      this.selectedFile= target.files[0];
    }
    this.selectedFileName= this.selectedFile.name;
    this.fileSelected= true;
  }

  onUpload() {
    this.videoService.uploadThumbnail(this.selectedFile, this.videoId).subscribe({
      next: value=> {
        this.snackBar.open("thumbnail uploaded successfully!", "OK");
        console.log("video id sanck bar "+this.videoId);
      },
      error: err=> {
        console.log(err);
      }
      
    })

  }
}
