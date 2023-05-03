import axios from "axios";

export class ViacepService {

  url = 'https://viacep.com.br/ws/'

  findZipCode(cep){
    return axios.get(`${this.url}${cep}/json/`)
  }
}