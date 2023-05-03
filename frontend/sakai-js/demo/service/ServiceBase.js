import axios from 'axios';

export class ServiceBase {

  constructor(urlBase){
    this.url = 'http://localhost:8080/api/' + urlBase
  }

  findAll() {
    return axios.get(this.url)
  }

  insert(object){
    return axios.post(this.url,object)
  }

  update(object){
    return axios.put(this.url,object)
  }

  delete(id){
    return axios.delete(this.url+'/'+ id)
  }
}