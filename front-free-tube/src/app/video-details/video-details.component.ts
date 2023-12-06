import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { VideoService } from '../services/video.service';
import { UserService } from '../services/user.service';

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
  videoUserId: string= '';
  videoLikes: number= 0;
  videoDislikes: number= 0;
  videoViewCount: number= 0;
  videoTags: Array<string>= [];
  userSubscribers: number= 0;

  isLikeVideo: boolean= false;
  isDislikeVideo: boolean= false;

  showSubscribeButton: boolean= true;
  showUnSubscribeButton: boolean= false;



  constructor(private routeActivate: ActivatedRoute,
              private videoService: VideoService,
              private userService: UserService) {
    this.videoId= this.routeActivate.snapshot.params['videoId'];

    this.videoService.getDetailsVideo(this.videoId).subscribe({
      next: data=> {
        if (data) {
          this.videoUrl = data.videoUrl;
          this.videoTitle = data.title;
          this.videoDescription = data.description;
          this.videoTags = data.tags;
          this.videoLikes= data.likes;
          this.videoDislikes= data.dislikes;
          this.videoViewCount= data.viewCount;
          this.videoUserId= data.userId;
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
    this.userService.getUser("6564d83dc08bec6794ad1680").subscribe({
      next: data=> {
        this.userSubscribers= data.subscribers.length;
        if (data.likesVideos.includes(this.videoId)) {
          this.isLikeVideo= true;
        }
        if (data.dislikesVideos.includes(this.videoId)) {
          this.isDislikeVideo= true; 
        }
        console.log(this.userSubscribers); 
      },
      error: err=> {
        console.log(err);
      }
    })
  }

  likeVideo() {
    this.videoService.likeVideo(this.videoId).subscribe({
      next: data=> {
        console.log(data);
        this.videoLikes= data.likes;
        this.videoDislikes= data.dislikes;
        this.isLikeVideo= true;
        this.isDislikeVideo= false;
      }, 
      error: err=> {
        console.log(err);
      }
    })
  }
  dislikeVideo() {
    this.videoService.dislikeVideo(this.videoId).subscribe({
      next: data=> {
        console.log(data);
        this.videoDislikes= data.dislikes; 
        this.videoLikes= data.likes;
        this.isLikeVideo= false;
        this.isDislikeVideo= true;
      }, 
      error: err=> {
        console.log(err);
      }
    })
  }
  subscribeToUser() {
    this.userService.subscribeToUser(this.videoUserId).subscribe({
      next: data=> {
        console.log(data.id);
        this.userSubscribers= data.subscribers.length;
        this.showSubscribeButton= false;
        this.showUnSubscribeButton= true;

        console.log("subscribe count: "+this.userSubscribers);
      },
      error: err=> {
        console.log(err);
      }
    })
  }

  unSubscribeToUser() {
    this.userService.unSubscribeToUser(this.videoUserId).subscribe({
      next: data=> {
        this.showSubscribeButton= true;
        this.showUnSubscribeButton= false;
        this.userSubscribers= data.subscribers.length;

        console.log("subscribe count: "+this.userSubscribers);
      }
    })
  }

}
