import { Injectable } from '@nestjs/common';
import {
  S3Client,
  PutObjectCommand,
  GetObjectCommand,
} from '@aws-sdk/client-s3';
import { getSignedUrl } from '@aws-sdk/s3-request-presigner';
import { ConfigService } from '@nestjs/config';

@Injectable()
export class AwsService {
  private readonly s3Client: S3Client;
  constructor(private readonly config: ConfigService) {
    this.s3Client = new S3Client({
      region: config.getOrThrow('AWS_REGION'),
      credentials: {
        accessKeyId: config.getOrThrow('AWS_ACCESS_KEY_ID'),
        secretAccessKey: config.getOrThrow('AWS_SECRET_ACCESS_KEY'),
      },
    });
  }

  async getObjectUrl(key: string): Promise<string> {
    const command = new GetObjectCommand({
      Bucket: this.config.getOrThrow('AWS_S3_BUCKET_NAME'),
      Key: key,
    });

    try {
      const url = await getSignedUrl(this.s3Client, command, {
        expiresIn: 3600,
      });
      return url;
    } catch (error) {
      console.error('Error getting signed URL', error);
      throw new Error('Could not generate signed URL');
    }
  }

  async putObjectUrl(key: string): Promise<string> {
    const command = new PutObjectCommand({
      Bucket: this.config.getOrThrow('AWS_S3_BUCKET_NAME'),
      Key: key,
      ContentType: 'jpg',
    });

    try {
      const url = await getSignedUrl(this.s3Client, command, {
        expiresIn: 3000,
      });
      return url;
    } catch (error) {
      console.error('Error getting signed URL', error);
      throw new Error('Could not generate signed URL');
    }
  }
}
