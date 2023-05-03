import { Button } from 'primereact/button';
import { Column } from 'primereact/column';
import { DataTable } from 'primereact/datatable';
import { Dialog } from 'primereact/dialog';
import { InputText } from 'primereact/inputtext';
import { Toast } from 'primereact/toast';
import { Toolbar } from 'primereact/toolbar';
import { classNames } from 'primereact/utils';
import { InputMask } from 'primereact/inputmask';
import { Password } from 'primereact/password';
import React, { use, useEffect, useRef, useState } from 'react';
import { UserService } from '../../../demo/service/UserService';
import { CityService } from '../../../demo/service/CityService';
import { ViacepService } from '../../../demo/service/api/ViacepService';


const User = () => {
    let newObject = {
        name: '',
        cpf:'',
        password:'',
        email:'',
        address:'',
        zipCode:'',
        city:null
    };

    const [objects, setObjects] = useState(null);
    const [ city , setCity] = useState(null)
    const [ error , setError] = useState(true)
    const [ passwordValid , setPasswordValid] = useState(false)
    const [objectDialog, setObjectDialog] = useState(false);
    const [deleteObjectDialog, setDeleteObjectDialog] = useState(false);
    const [object, setObject] = useState(newObject);
    const [submitted, setSubmitted] = useState(false);
    const [globalFilter, setGlobalFilter] = useState(null);
    const toast = useRef(null);
    const dt = useRef(null);
    const objectService = new UserService();
    const cityService = new CityService();
    const viaCepService = new ViacepService()

    useEffect(() => {
        if(objects == null){
          objectService.findAll().then(response => {
                setObjects(response.data)
            })
        }
        cityService.findAll().then(response => {
          setCity(response.data)
        })
    }, [objects]);

    const openNew = () => {
        setObject(newObject);
        setSubmitted(false);
        setObjectDialog(true);
    };

    const hideDialog = () => {
        setSubmitted(false);
        setObjectDialog(false);
    };

    const hideDeleteObjectDialog = () => {
        setDeleteObjectDialog(false);
    };

    const saveObject = () => {
        setSubmitted(true);

        if (object.name.trim()) {
            let _object = {...object};
            if (object.id) {
                objectService.update(_object).then(data => {
                    toast.current.show({ severity: 'success', summary: 'Successful', detail: 'Alterado com sucesso', life: 3000 });
                    setObjects(null)
                })             
            } else {
                objectService.insert(_object).then(data => {
                    toast.current.show({ severity: 'success', summary: 'Successful', detail: 'Adicionado com sucesso', life: 3000 });
                    setObjects(null)
                })
            }
            setObjectDialog(false)
            setObject(newObject);
        }
    };

    const editObject = (object) => {
        setObject({ ...object });
        setObjectDialog(true);
    };

    const confirmDeleteObject = (object) => {
      setObject(object);
      setDeleteObjectDialog(true);
        
    };

    const deleteObject = () => {
      objectService.delete(object.id).then(data => {
        toast.current.show({ severity: 'success', summary: 'Successful', detail: 'Usuário deletada', life: 3000 });
        setObjects(null);
        setDeleteObjectDialog(false);
      })
    }

    const onInputChange = (e, name) => {
      const val = (e.target && e.target.value) || '';
      let _object = { ...object };
      _object[`${name}`] = val;

      setObject(_object);
    };

    const isValidCep = (rowData) => {
      if(rowData.length === 8){
        viaCepService.findZipCode(rowData).then(response => {

          if(response.status === 200 && !response.data.error) {
            object.zipCode = response.data.cep
            object.address = response.data.logradouro
            setError(false)
            return;
          }
        })
        .catch(error => {
          console.error('CEP inválido ', error);
        })
      } 

      object.address = '';
      object.zipCode = '';
      setError(true)
    }

    const comparePassword = (e) => {
      if(e === object.address) {
        return setPasswordValid(true);
      }
      return setPasswordValid(false);
    }

    const leftToolbarTemplate = () => {
      return (
        <React.Fragment>
          <div className="my-2">
              <Button label="Criar usuário" icon="pi pi-plus" severity="sucess" className="mr-2" onClick={openNew} />                  
          </div>
        </React.Fragment>
      );
    };

    const idBodyTemplate = (rowData) => {
      return (
          <>
              <span className="p-column-title">ID</span>
              {rowData.id}
          </>
      )
    }

    const nameBodyTemplate = (rowData) => {
      return (
        <>
            <span className="p-column-title">Nome</span>
            {rowData.name}
        </>
      );
    };

    const cpfBodyTemplate = (rowData) => {
      return (
        <>
          <span className="p-column-title">CPF</span>
          {rowData.cpf}
        </>
      );   
    }

    const emailBodyTemplate = (rowData) => {
      return (
        <>
          <span className="p-column-title">Email</span>
          {rowData.email}
        </>
      );   
    }

    // const passwordBodyTemplate = (rowData) => {
    //   return (
    //     <>
    //       <span className="p-column-title">Nome</span>
    //       {rowData.password}
    //     </>
    //   );   
    // }

    const addressBodyTemplate = (rowData) => {
      return (
        <>
          <span className="p-column-title">Endereço</span>
          {rowData.address}
        </>
      );   
    }

    const zipCodeBodyTemplate = (rowData) => {
      return (
        <>
          <span className="p-column-title">CEP</span>
          {rowData.zipCode}
        </>
      );   
    }

    const cityBodyTemplate = (rowData) => {
      return (
        <>
          <span className="p-column-title">Cidade</span>
          {rowData.city}
        </>
      );   
    }

    const actionBodyTemplate = (rowData) => {
        return (
            <>
                <Button icon="pi pi-pencil" severity="success" rounded className="mr-2" onClick={() => editObject(rowData)} />
                <Button icon="pi pi-trash" severity="warning" rounded onClick={() => confirmDeleteObject(rowData)} />
            </>
        );
    };

    const header = (
        <div className="flex flex-column md:flex-row md:justify-content-between md:align-items-center">
            <h5 className="m-0">Gerenciar usuários</h5>
            <span className="block mt-2 md:mt-0 p-input-icon-left">
                <i className="pi pi-search" />
                <InputText type="search" onChange={(e) => setGlobalFilter(e.target.value)} placeholder="Procurar..." />
            </span>
        </div>
    );

    const objectDialogFooter = (
        <>
            <Button label="Cancelar" icon="pi pi-times" text onClick={hideDialog} />
            <Button label="Salvar" icon="pi pi-check" text onClick={saveObject} />
        </>
    );

    const deleteObjectDialogFooter = (
        <>
            <Button label="Não" icon="pi pi-times" text onClick={hideDeleteObjectDialog} />
            <Button label="Sim" icon="pi pi-check" text onClick={deleteObject} />
        </>
    );

    return (
        <div className="grid crud-demo">
            <div className="col-12">
                <div className="card">
                    <Toast ref={toast} />
                    <Toolbar className="mb-4" left={leftToolbarTemplate}></Toolbar>

                    <DataTable
                        ref={dt}
                        value={objects}
                        selection={setObjects}
                        onSelectionChange={(e) => setSelectedProducts(e.value)}
                        dataKey="id"
                        paginator
                        rows={10}
                        rowsPerPageOptions={[5, 10, 25]}
                        className="datatable-responsive"
                        paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink CurrentPageReport RowsPerPageDropdown"
                        currentPageReportTemplate="Showing {first} to {last} of {totalRecords} products"
                        globalFilter={globalFilter}
                        emptyMessage="Não há cadastro"
                        header={header}
                        responsiveLayout="scroll"
                    >
                        
                        <Column field="id" header="Id" sortable body={idBodyTemplate} headerStyle={{ minWidth: '1rem' }}></Column>
                        <Column field="name" header="Nome" sortable body={nameBodyTemplate} headerStyle={{ minWidth: '1rem' }}></Column>
                        <Column field="cpf" header="CPF" sortable body={cpfBodyTemplate} headerStyle={{ minWidth: '1rem' }}></Column>
                        {/* <Column field="password" header="Senha" sortable body={passwordBodyTemplate} headerStyle={{ minWidth: '1rem' }}></Column> */}
                        <Column field="email" header="Email" sortable body={emailBodyTemplate} headerStyle={{ minWidth: '1rem' }}></Column>
                        <Column field="address" header="Endereço" sortable body={addressBodyTemplate} headerStyle={{ minWidth: '1rem' }}></Column>
                        <Column field="zipCode" header="CEP" sortable body={zipCodeBodyTemplate} headerStyle={{ minWidth: '1rem' }}></Column>
                        <Column field="city" header="Cidade" sortable body={cityBodyTemplate} headerStyle={{ minWidth: '1rem' }}></Column>
                        <Column body={actionBodyTemplate}></Column>
                    </DataTable>

                    <Dialog visible={objectDialog} style={{ width: '450px' }} header="Detalhes do usuário" modal className="p-fluid" footer={objectDialogFooter} onHide={hideDialog}>
                      <div className="field">
                          <label htmlFor="name">Name</label>
                          <InputText id="name" value={object.name} onChange={(e) => onInputChange(e, 'name')} required autoFocus className={classNames({ 'p-invalid': submitted && !object.name })} />
                          {submitted && !object.name && <small className="p-invalid">*Nome é necessário</small>}
                      </div>

                      <div className="field">
                          <label htmlFor="name">CPF</label>
                          <InputMask value={object.cpf} onChange={(e) => onInputChange(e, 'cpf')} mask="999.999.999-99" placeholder="999.999.999-99" />
                      </div>

                      <div className="field">
                          <label htmlFor="name">Senha</label>
                          <Password value={object.password} onChange={(e) => onInputChange(e, 'password')} toggleMask required className={classNames({ 'p-invalid': submitted && !object.password && !passwordValid })} />
                          {submitted && !object.password && <small className="p-invalid">*Campo obrigatório</small>}
                      </div>

                      <div className="field">
                          <label htmlFor="name">Repita a senha</label>
                          <Password onChange={(e) => comparePassword(e.target.value)} toggleMask required className={classNames({ 'p-invalid': submitted && !passwordValid && !object.password})}/>
                          {submitted && !passwordValid && <small className="p-invalid">*Senhas diferentes</small>}
                      </div>

                      <div className='field'>
                        <label htmlFor="name">Email</label>
                        <InputText type='email' value={object.email} onChange={(e) => onInputChange(e, 'email')} />
                      </div>
                      <div className="field">
                          <label htmlFor="name">CEP</label>
                          <InputText id="zipCode" onChange={(e) => isValidCep(e.target.value)} mask="99999-999" placeholder="99999-999"/>                      
                      </div>
                      <div className="field">
                          <label htmlFor="name">Endereço</label>
                          <InputText id="address" value={object.address} onChange={(e) => onInputChange(e, 'address')} />                      
                      </div>
                    </Dialog>

                    <Dialog visible={deleteObjectDialog} style={{ width: '450px' }} header="Confirm" modal footer={deleteObjectDialogFooter} onHide={objectDialogFooter}>
                      <div className="flex align-items-center justify-content-center">
                        <i className="pi pi-exclamation-triangle mr-3" style={{ fontSize: '1rem' }} />
                        {object && <span>Deseja Excluir?</span>}
                      </div>
                    </Dialog>
                </div>
            </div>
        </div>
    );
};

export default User;
