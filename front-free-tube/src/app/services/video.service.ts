import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { UploadVideoResponse } from '../upload-video/UploadVideoResponse';
import { VideoDto } from '../model/video-dto';
import { UserDto } from '../model/user-dto';

@Injectable({
  providedIn: 'root'
})
export class VideoService {

  constructor(private http: HttpClient) { }

  public uploadVideo(fileEntry: File): Observable<UploadVideoResponse> {
    const formData = new FormData();
    formData.append('file', fileEntry, fileEntry.name);

    return this.http.post<UploadVideoResponse>(environment.backEndVideoHost+"/upload", formData)
  }

  public uploadThumbnail(fileEntry: File, videoId: string): Observable<any> {
    const formData= new FormData();
    formData.append('file', fileEntry, fileEntry.name);
    formData.append('videoId', videoId);

    return this.http.patch(environment.backEndVideoHost+"/thumbnail", formData); 
  }

  public getDetailsVideo(videoId: string): Observable<VideoDto>{ 
    return this.http.get<VideoDto>(environment.backEndVideoHost+"/details/"+videoId);  
  }

  public getAllVideos(): Observable<Array<VideoDto>>{
    return this.http.get<Array<VideoDto>>(environment.backEndVideoHost+"/allVideo");
  }

  public saveVideo(videoDto: VideoDto): Observable<VideoDto> { 
    return this.http.put<VideoDto>(environment.backEndVideoHost+"/saveDetails", videoDto); 
  }

  public likeVideo(videoId: string): Observable<VideoDto> {
    return this.http.patch<VideoDto>(environment.backEndVideoHost+"/"+videoId+"/like", null);
  }
  
  public dislikeVideo(videoId: string): Observable<VideoDto> {
    return this.http.patch<VideoDto>(environment.backEndVideoHost+"/"+videoId+"/dislike", null);
  }
}
