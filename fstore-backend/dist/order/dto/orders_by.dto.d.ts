export declare class OrderAddress {
    address: string;
}
export declare class OrderProduct {
    name: string;
    size: string;
    color: string;
    category: string;
    price: number;
    quantity: number;
    thumbnailUrl: string;
}
export declare class OrderByDto {
    id: number;
    orderNo: string;
    orderId: string;
    totalPrice: number;
    createdAt: Date;
    quantity: number;
    address: OrderAddress;
    orderProducts: OrderProduct;
}
