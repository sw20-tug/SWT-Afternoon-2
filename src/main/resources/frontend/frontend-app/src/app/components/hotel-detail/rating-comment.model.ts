import {FacilityItem} from "./facility-item.model";
import {Facilities} from "./facilities.enum";

export class RatingComment {
    public name: string;
    public comment: string;
    public rating: number;

    constructor(name: string, comment: string, rating: number) {
        this.name = name;
        this.comment = comment;
        this.rating = rating;
    }

  static MapComment(comment: any) {
    var mappedHotel = new RatingComment(comment.name, comment.comment, comment.rating);

    return mappedHotel;
  }
}
