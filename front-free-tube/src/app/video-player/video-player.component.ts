import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-video-player',
  templateUrl: './video-player.component.html',
  styleUrls: ['./video-player.component.scss']
})
export class VideoPlayerComponent implements OnInit {

  @Input()
  videoUrl!: string;
  @Input()
  videoHeight!: number | 0;
  @Input()
  videoWidth!: number | 0;

  constructor() {}
  ngOnInit(): void {
    console.log("video url: "+this.videoUrl);
  }


}
