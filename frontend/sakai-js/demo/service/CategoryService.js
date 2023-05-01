import axios from "axios"

export class CategoryService{
  url = "http://localhost:8080/api/"

  findAll(){
    return axios.get(this.url + 'categories')
  }

  insert(object){
    return axios.post(this.url + 'categories', object)
  }

  update(object){
    return axios.put(this.url + 'categories', object)
  }

  delete(id){
    return axios.delete(this.url + 'categories/' + id)
  }
}