import axios from "axios";

export class CityService {
  url = 'http://localhost:8080/api/'

  findAll(){
    return axios.get(this.url+'cities')
  }

  insert(object){
    return axios.post(this.url+'cities', object)
  }

  update(object){
    return axios.put(this.url+'cities', object)
  }

  delete(id){
    return axios.delete(this.url+'cities/ '+ id)
  }
}