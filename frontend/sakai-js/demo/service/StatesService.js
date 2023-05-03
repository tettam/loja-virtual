import axios from 'axios';
import { ServiceBase } from './ServiceBase';

export class StatesService extends ServiceBase{

  constructor(){
    super("states");
  }
}