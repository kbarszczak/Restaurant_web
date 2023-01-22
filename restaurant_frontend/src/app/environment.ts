export const environment = {
    baseUrl: 'http://localhost:9090'
};

export enum ApiPaths {
    Auth = '/api/v1/auth/login',
    Register = "/api/v1/auth/register",
    DishesAll = "/api/v1/dishes",
    Dish = "/api/v1/dishes",
    Review = "/api/v1/dishes",
    CanReview = "/api/v1/users/orders/exist",
}