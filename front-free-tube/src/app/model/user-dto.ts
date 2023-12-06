import { VideoDto } from "./video-dto";

export interface UserDto{
    id: string;	
    firstname: string;	
    lastname: string;		
    email: string;	
    subscribeToUsers: Array<string>;	
    subscribers: Array<string>;
    videoHistory: Array<string>;	
    likesVideos: Array<string>;
    dislikesVideos: Array<string>;
}