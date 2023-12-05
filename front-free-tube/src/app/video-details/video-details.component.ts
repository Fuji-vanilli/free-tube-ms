import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { VideoService } from '../services/video.service';

@Component({
  selector: 'app-video-details',
  templateUrl: './video-details.component.html',
  styleUrls: ['./video-details.component.scss']
})
export class VideoDetailsComponent implements OnInit {

  videoId!: string;
  videoUrl!: string;
  videoTitle: string= '';
  videoDescription: string= '';
  videoTags: Array<string>= [];



  constructor(private routeActivate: ActivatedRoute,
              private videoService: VideoService) {
    this.videoId= this.routeActivate.snapshot.params['videoId'];
    this.videoService.getDetailsVideo(this.videoId).subscribe({
      next: data=> {
        if (data) {
          this.videoUrl = data.videoUrl;
          this.videoTitle = data.title;
          this.videoDescription = data.description;
          this.videoTags = data.tags;
          console.log("video details url: " + this.videoUrl);
        } else {
          console.error("No data received from the service");
        }
      },
      error: err=> {
        console.log(err); 
      }
    })

  }
  ngOnInit(): void {
    
  }

}
