import axios from "axios";

export class ProductsService {
  url = 'hhtp://localhost:8080/api/products'

  findAll(){
    return axios.get(this.url)
  }

  insert(object){
    return axios.post(this.url + '/', object)
  }

  update(object){
    return axios.put(this.url + '/', object)
  }

  delete(id){
    return axios.delete(this.url + '/' + id)
  }
}