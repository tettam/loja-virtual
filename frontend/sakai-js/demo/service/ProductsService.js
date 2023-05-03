import axios from "axios";

export class ProductsService {
  url = 'http://localhost:8080/api/'

  findAll(){
    return axios.get(this.url + 'products')
  }

  insert(object){
    return axios.post(this.url + 'products', object)
  }

  update(object){
    return axios.put(this.url + 'products', object)
  }

  delete(id){
    return axios.delete(this.url + 'products/' + id)
  }
}