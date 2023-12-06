export interface VideoDto {
    id: string;
    description: string;
    title: string;
    userId: string;
    likeCount: number;
    dislikeCount: number;
    likes: number;
    dislikes: number;
    viewCount: number;
    videoUrl: '';
    tags: Array<string>;
    videoStatus: string;
    thumbnailUrl: string;
}