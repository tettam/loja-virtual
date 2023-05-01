import axios from 'axios';

export class StatesService {
  url = 'http://localhost:8080/api/'

  states() {
    return axios.get(this.url+'states')
  }

  insert(object){
    return axios.post(this.url+'states',object)
  }

  update(object){
    return axios.put(this.url+'states',object)
  }

  delete(id){
    return axios.delete(this.url+'states'+ id)
  }
}