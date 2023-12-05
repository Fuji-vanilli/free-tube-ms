import { Component, OnInit } from '@angular/core';
import { VideoService } from '../services/video.service';
import { VideoDto } from '../model/video-dto';

@Component({
  selector: 'app-featured',
  templateUrl: './featured.component.html',
  styleUrls: ['./featured.component.scss']
})
export class FeaturedComponent implements OnInit {

  feauteredVideos: Array<VideoDto>= [];

  constructor(private videoService: VideoService) {}

  ngOnInit(): void {
    this.videoService.getAllVideos().subscribe({
      next: response=> {
        this.feauteredVideos= response;
        console.log(this.feauteredVideos);
      },
      error: err=> {
        console.log(err);
      }
    })
  }

}
