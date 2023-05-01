import axios from "axios";

export class CityService {
  url = 'http://localhost:3000/api/cities'

  findAll(){
    axios.get(this.url)
    .then(response => console.log(response.data))
    .catch(error => console.log(error))
  }

  insert(object){
    axios.post(url, object)
    .then(response => console.log(response.data))
    .catch(error => console.log(error))
  }

  update(object){
    axios.put(url, object)
    .then(response => console.log(response.data))
    .catch(error => console.log(error))
  }

  delete(id){
    axios.delete(url, id)
    .then(response => console.log(response.data))
    .catch(error => console.log(error))
  }
}