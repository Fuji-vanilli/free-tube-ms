import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UploadVideoComponent } from './upload-video/upload-video.component';
import { HeaderComponent } from './header/header.component';
import { SaveVideoDetailsComponent } from './save-video-details/save-video-details.component';
import { VideoDetailsComponent } from './video-details/video-details.component';

const routes: Routes = [
  { path: "upload-video", component: UploadVideoComponent },
  { path: "save-video-details/:videoId", component:SaveVideoDetailsComponent },
  { path: "video-details/:videoId", component: VideoDetailsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
