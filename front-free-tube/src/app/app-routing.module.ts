import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UploadVideoComponent } from './upload-video/upload-video.component';
import { HeaderComponent } from './header/header.component';
import { SaveVideoDetailsComponent } from './save-video-details/save-video-details.component';
import { VideoDetailsComponent } from './video-details/video-details.component';
import { HomeComponent } from './home/home.component';
import { SubscriptionsComponent } from './subscriptions/subscriptions.component';
import { HistoryComponent } from './history/history.component';
import { LikedVideosComponent } from './liked-videos/liked-videos.component';
import { FeaturedComponent } from './featured/featured.component';

const routes: Routes = [
  { path: '', component: HomeComponent, children: [
    { path: 'featured', component: FeaturedComponent},
    { path: 'subscriptions', component: SubscriptionsComponent},
    { path: 'history', component: HistoryComponent},
    { path: 'liked-video', component: LikedVideosComponent},
  ]},
  { path: "upload-video", component: UploadVideoComponent },
  { path: "save-video-details/:videoId", component:SaveVideoDetailsComponent },
  { path: "video-details/:videoId", component: VideoDetailsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
