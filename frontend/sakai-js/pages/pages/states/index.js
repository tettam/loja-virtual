import { Button } from 'primereact/button';
import { Column } from 'primereact/column';
import { DataTable } from 'primereact/datatable';
import { Dialog } from 'primereact/dialog';
import { InputText } from 'primereact/inputtext';
import { Toast } from 'primereact/toast';
import { Toolbar } from 'primereact/toolbar';
import { classNames } from 'primereact/utils';
import React, { useEffect, useRef, useState } from 'react';
import { StatesService } from '../../../demo/service/StatesService';

const States = () => {
    let newObject = {
        name: '',
        acronym: ''
    };

    const [objects, setObjects] = useState(null);
    const [objectDialog, setObjectDialog] = useState(false);
    const [deleteObjectDialog, setDeleteObjectDialog] = useState(false);
    const [object, setObject] = useState(newObject);
    const [submitted, setSubmitted] = useState(false);
    const [globalFilter, setGlobalFilter] = useState(null);
    const toast = useRef(null);
    const dt = useRef(null);
    const objectService = new StatesService()

    useEffect(() => {
        if(objects == null){
            objectService.states().then(res => {
                setObjects(res.data)
            })
        }
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
                    toast.current.show({ severity: 'success', summary: 'Successful', detail: 'Product Updated', life: 3000 });
                    setObjects(null)
                })             
            } else {
                objectService.insert(_object).then(data => {
                    toast.current.show({ severity: 'success', summary: 'Successful', detail: 'Product Created', life: 3000 });
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
            toast.current.show({ severity: 'success', summary: 'Successful', detail: 'Product Deleted', life: 3000 });
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

    const leftToolbarTemplate = () => {
        return (
            <React.Fragment>
                <div className="my-2">
                    <Button label="Novo Serviço" icon="pi pi-plus" severity="sucess" className="mr-2" onClick={openNew} />                  
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

    const acronymBodyTemplate = (rowData) => {
        return (
            <>                                                                                                                                                              
                <span className="p-column-title">Sigla</span>
                {rowData.acronym}
               
            </>
        );
    };

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
            <h5 className="m-0">Manage Products</h5>
            <span className="block mt-2 md:mt-0 p-input-icon-left">
                <i className="pi pi-search" />
                <InputText type="search" onChange={(e) => setGlobalFilter(e.target.value)} placeholder="Search..." />
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
                        emptyMessage="Sem objetos cadastrados"
                        header={header}
                        responsiveLayout="scroll"
                    >
                        
                        <Column field="id" header="Id" sortable body={idBodyTemplate} headerStyle={{ minWidth: '1rem' }}></Column>
                        <Column field="name" header="name" sortable body={nameBodyTemplate} headerStyle={{ minWidth: '1rem' }}></Column>
                        <Column field="acronym" header="acronym" sortable body={acronymBodyTemplate} headerStyle={{ minWidth: '1rem' }}></Column>
                        <Column body={actionBodyTemplate}></Column>
                    </DataTable>

                    <Dialog visible={objectDialog} style={{ width: '450px' }} header="Detalhes do Serviço" modal className="p-fluid" footer={objectDialogFooter} onHide={hideDialog}>
                        <div className="field">
                            <label htmlFor="name">Name</label>
                            <InputText id="name" value={object.name} onChange={(e) => onInputChange(e, 'name')} required autoFocus className={classNames({ 'p-invalid': submitted && !object.name })} />
                            {submitted && !object.name && <small className="p-invalid">Name is required.</small>}
                        </div>

                        <div className="field">
                            <label htmlFor="acronym">Sigla</label>
                            <InputText id="acronym" value={object.acronym} onChange={(e) => onInputChange(e, 'acronym')} required/>
                        </div>
                    </Dialog>

                    <Dialog visible={deleteObjectDialog} style={{ width: '450px' }} header="Confirm" modal footer={deleteObjectDialogFooter} onHide={objectDialogFooter}>
                        <div className="flex align-items-center justify-content-center">
                            <i className="pi pi-exclamation-triangle mr-3" style={{ fontSize: '2rem' }} />
                            {object && <span>Deseja Excluir?</span>}
                        </div>
                    </Dialog>
                </div>
            </div>
        </div>
    );
};

export default States;
