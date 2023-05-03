import axios from "axios"
import { ServiceBase } from './ServiceBase';

export class CategoryService extends ServiceBase{
  
  constructor(){
    super('categories')
  }
}