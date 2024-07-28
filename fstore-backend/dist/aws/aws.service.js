"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.AwsService = void 0;
const common_1 = require("@nestjs/common");
const client_s3_1 = require("@aws-sdk/client-s3");
const s3_request_presigner_1 = require("@aws-sdk/s3-request-presigner");
const config_1 = require("@nestjs/config");
let AwsService = class AwsService {
    constructor(config) {
        this.config = config;
        this.s3Client = new client_s3_1.S3Client({
            region: config.getOrThrow('AWS_REGION'),
            credentials: {
                accessKeyId: config.getOrThrow('AWS_ACCESS_KEY_ID'),
                secretAccessKey: config.getOrThrow('AWS_SECRET_ACCESS_KEY'),
            },
        });
    }
    async getObjectUrl(key) {
        const command = new client_s3_1.GetObjectCommand({
            Bucket: this.config.getOrThrow('AWS_S3_BUCKET_NAME'),
            Key: key,
        });
        try {
            const url = await (0, s3_request_presigner_1.getSignedUrl)(this.s3Client, command, {
                expiresIn: 3600,
            });
            return url;
        }
        catch (error) {
            console.error('Error getting signed URL', error);
            throw new Error('Could not generate signed URL');
        }
    }
    async putObjectUrl(key) {
        const command = new client_s3_1.PutObjectCommand({
            Bucket: this.config.getOrThrow('AWS_S3_BUCKET_NAME'),
            Key: key,
            ContentType: 'jpg',
        });
        try {
            const url = await (0, s3_request_presigner_1.getSignedUrl)(this.s3Client, command, {
                expiresIn: 3000,
            });
            return url;
        }
        catch (error) {
            console.error('Error getting signed URL', error);
            throw new Error('Could not generate signed URL');
        }
    }
};
exports.AwsService = AwsService;
exports.AwsService = AwsService = __decorate([
    (0, common_1.Injectable)(),
    __metadata("design:paramtypes", [config_1.ConfigService])
], AwsService);
//# sourceMappingURL=aws.service.js.map