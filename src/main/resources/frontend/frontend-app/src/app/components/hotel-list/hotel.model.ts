export class Hotel {
  public name: string;
  public description: string;
  public price: string;
  public imagePath: string;
  public stars: number;
  public rate: number;
  public city: string;

  constructor(name: string, description: string, price: string, imagePath: string, stars: number, rate: number, city: string) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.imagePath = imagePath;
    this.stars = stars;
    this.rate = rate;
    this.city = city;
  }

   getRating() {
   // console.log("rate is ", this.rate);
    if (this.rate <= 10 && this.rate >= 8) {
      return "Excellent";
    } else if (this.rate < 8 && this.rate >= 5) {
      return "Good"
    } else return "Poor";
  }
}
