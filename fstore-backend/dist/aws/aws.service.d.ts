import { ConfigService } from '@nestjs/config';
export declare class AwsService {
    private readonly config;
    private readonly s3Client;
    constructor(config: ConfigService);
    getObjectUrl(key: string): Promise<string>;
    putObjectUrl(key: string): Promise<string>;
}
