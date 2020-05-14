export class RatingComment {
    public name: string;
    public comment: string;
    public rating: number;

    constructor(name: string, comment: string, rating: number) {
        this.name = name;
        this.comment = comment;
        this.rating = rating;
    }
}