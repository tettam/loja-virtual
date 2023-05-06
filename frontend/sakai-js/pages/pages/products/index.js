import { Button } from 'primereact/button';
import { Column } from 'primereact/column';
import { DataTable } from 'primereact/datatable';
import { Dialog } from 'primereact/dialog';
import { InputText } from 'primereact/inputtext';
import { Toast } from 'primereact/toast';
import { Toolbar } from 'primereact/toolbar';
import { classNames } from 'primereact/utils';
import { InputTextarea } from 'primereact/inputtextarea';
import { InputNumber } from 'primereact/inputnumber';
import React, { useEffect, useRef, useState } from 'react';
import { Dropdown } from 'primereact/dropdown'; 
import { ProductsService } from '../../../demo/service/ProductsService';
import { CategoryService } from '../../../demo/service/CategoryService';
import { BrandsService } from '../../../demo/service/BrandsService';

const Products = () => {
    let newObject = {
      name: '',
      description: '',
      costPrice: '',
      salePrice: '',
      brand: null,
      category: null,
      images: null,
    };

    const [objects, setObjects] = useState(null);
    const [category , setCategory] = useState(null);
    const [brand, setBrand] = useState(null)
    const [objectDialog, setObjectDialog] = useState(false);
    const [deleteObjectDialog, setDeleteObjectDialog] = useState(false);
    const [object, setObject] = useState(newObject);
    const [submitted, setSubmitted] = useState(false);
    const [globalFilter, setGlobalFilter] = useState(null);
    const toast = useRef(null);
    const dt = useRef(null);
    const objectService = new ProductsService();
    const categoryService = new CategoryService();
    const brandService = new BrandsService();

    useEffect(() => {
        if(objects == null){
            objectService.findAll().then(response => {
                setObjects(response.data);
            })       
        }
        categoryService.findAll().then(response => {
          setCategory(response.data)
        })
        brandService.findAll().then(response => {
          setBrand(response.data)
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
        toast.current.show({ severity: 'success', summary: 'Successful', detail: 'Produto deletado', life: 3000 });
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
                  <Button label="Criar produto" icon="pi pi-plus" severity="sucess" className="mr-2" onClick={openNew} />                  
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

    const categoryBodyTemplate = (rowData) => {
      return (
        <>
            <span className='p-column-title'>Categoria</span>
            {rowData.category && (rowData.category.name)}
        </>
      )
    }

    const brandBodyTemplate = (rowData) => {
      return (
        <>
            <span className='p-column-title'>Marca</span>
            {rowData.brand && (rowData.brand.name)}
        </>
      )
    }

    const costPriceBodyTemplate = (rowData) => {
      const value = rowData.costPrice;
      return (
        <>
          <span className='p-column-title'></span>
          {rowData.costPrice && (value.toLocaleString('pt-BR', {style: 'currency', currency: 'BRL'}))}  
        </>
      )
    }

    const salePriceBodyTemplate = (rowData) => {
      const value = rowData.salePrice;
      return (
        <>
          <span className='p-column-title'></span>
          {rowData.salePrice && (value.toLocaleString('pt-BR', {style: 'currency', currency: 'BRL'}))}
        </>
      )
    }

    const descriptionBodyTemplate = (rowData) => {
      return (
        <>
          <span className='p-column-title'></span>
          {rowData.description}
        </>
      )
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
            <h5 className="m-0">Gerenciar produtos</h5>
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
                        <Column field="costPrice" header="Valor de Compra" sortable body={costPriceBodyTemplate} headerStyle={{ minWidth: '1rem' }} ></Column>
                        <Column field="salePrice" header="Valor de Venda" sortable body={salePriceBodyTemplate} headerStyle={{ minWidth: '1rem' }}></Column>
                        <Column field="category" header="Categoria" sortable body={categoryBodyTemplate} headerStyle={{ minWidth: '1rem' }}></Column>
                        <Column field="brand" header="Marca" sortable body={brandBodyTemplate} headerStyle={{ minWidth: '1rem' }}></Column>
                        <Column field="description" header="Descrição" sortable body={descriptionBodyTemplate} headerStyle={{ minWidth: '2rem' }}></Column>
                        <Column body={actionBodyTemplate}></Column>
                    </DataTable>

                    <Dialog visible={objectDialog} style={{ width: '450px' }} header="Detalhes do produto" modal className="p-fluid" footer={objectDialogFooter} onHide={hideDialog}>
                        <div className="field">
                          <label htmlFor="name">Name</label>
                          <InputText id="name" value={object.name} onChange={(e) => onInputChange(e, 'name')} required autoFocus className={classNames({ 'p-invalid': submitted && !object.name })} />
                          {submitted && !object.name && <small className="p-invalid">Nome é necessário</small>}                 
                        </div>

                        <div className="field">
                          <label htmlFor="name">Valor de Compra</label>
                          <InputNumber id='costPrice' value={object.costPrice} onValueChange={(e) => onInputChange(e, 'costPrice')} mode="currency" currency="BRL" locale="pt-br"/>
                        </div>

                        <div className="field">
                          <label htmlFor="name">Valor de Venda</label>
                          <InputNumber id='salePrice' value={object.salePrice} mode="currency" currency="BRL" locale="pt-br" onValueChange={(e) => onInputChange(e, 'salePrice')} />
                        </div>

                        <div className='field'>
                          <label htmlFor="name">Descrição</label>
                          <InputTextarea id='description' autoResize value={object.description} onChange={(e) => onInputChange(e, 'description')} rows={3} cols={20} maxLength={100}/>
                        </div>  

                        <div className="field">
                          <label htmlFor="name">Categoria</label>
                          <Dropdown value={object.category} onChange={(e) => onInputChange(e, 'category')} options={category} optionLabel="name" 
                          placeholder="Selecione a categoria" className="w-full md:w-14rem" filter />
                          {/* {submitted && !object.name && <small className="p-invalid">É necessário selecionar.</small>} */}
                        </div>

                        <div className="field">
                          <label htmlFor="name">Marca</label>
                          <Dropdown value={object.brand} onChange={(e) => onInputChange(e, 'brand')} options={brand} optionLabel="name" 
                          placeholder="Selecione a marca" className="w-full md:w-14rem" filter />
                          {/* {submitted && !object.name && <small className="p-invalid">É necessário selecionar.</small>} */}
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

export default Products;
