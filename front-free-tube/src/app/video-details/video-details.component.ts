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
  videoUrl: string= '../../assets/VID_97b2a5f1-e6fd-49ef-b9d2-95fb5b6fe3e9.mp4';
  videoTitle: string= '';
  videoDescription: string= '';
  videoTags: Array<string>= [];



  constructor(private routeActivate: ActivatedRoute,
              private videoService: VideoService) {
    this.videoId= this.routeActivate.snapshot.params['videoId'];

  }
  ngOnInit(): void {
    this.videoService.getDetailsVideo(this.videoId).subscribe({
      next: data=> {
        this.videoUrl= data.videoUrl;
        this.videoTitle= data.title;
        this.videoDescription= data.description; 
        this.videoTags= data.tags;
        console.log("video details url: "+this.videoUrl);
      },
      error: err=> {
        console.log(err); 
      }
    })
  }



}
