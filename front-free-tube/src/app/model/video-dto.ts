export interface VideoDto {
    id: string;
    description: string;
    title: string;
    userId: string;
    videoUrl: '';
    tags: Array<string>;
    videoStatus: string;
    thumbnailUrl: string;
}