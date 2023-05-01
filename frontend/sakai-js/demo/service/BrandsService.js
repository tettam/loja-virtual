import axios from "axios";

export class BrandsService {
  url = 'http://localhost:8080/api/'

  findAll(){
    return axios.get(this.url + 'brands')
  }

  insert(object){
    return axios.post(this.url + 'brands', object)
  }

  update(object){
    return axios.put(this.url + 'brands', object)
  }

  delete(id){
    return axios.delete(this.url + 'brands/' + id)
  }
}