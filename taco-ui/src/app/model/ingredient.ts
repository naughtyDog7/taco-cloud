export class Ingredient {
  id: String
  name: String;
  type: String;
  price: String;


  constructor(id: String, name: String, type: String, price: String) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.price = price;
  }
}
