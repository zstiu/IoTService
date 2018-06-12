/* eslint no-underscore-dangle: 0 */
import React, { Component } from 'react';
import {withRouter} from "react-router-dom";
import { Table, Pagination } from '@icedesign/base';
import IceContainer from '@icedesign/container';
import DataBinder from '@icedesign/data-binder';
import IceLabel from '@icedesign/label';
import FilterForm from './Filter';

@DataBinder({
  tableData: {
    // 详细请求配置请参见 https://github.com/axios/axios
    url: 'http://115.159.26.94:8080/order/',
    params: {
      complete: false,
    },
    defaultBindingData: {
      list: []
    },
    responseFormatter: (responseHandler, res, originResponse) => {
      // 拿到接口返回的 res 数据，做一些格式转换处理，使其符合 DataBinder 的要求
      // 最后再按照顺序丢到 responseHandler 方法里继续执行
      let list=[];
      res.data.forEach(d=>{
        list.push({...d,orderId:d.id});
      })
      res = {
        success: res.success,
        message: res.message,
        data: {
          list: list
        },
      };
      responseHandler(res, originResponse);
    },
  },
})
export default class EnhanceTable extends Component {
  static displayName = 'EnhanceTable';

  static defaultProps = {};

  constructor(props) {
    super(props);

    // 请求参数缓存
    this.queryCache = {};
    this.state = {
      filterFormValue: {},
      issearch:false,
    };
   
  }

  componentDidMount() {
    this.fetchData();
  }

  fetchData = () => {
    this.props.updateBindingData('tableData', {
      data: this.queryCache,
      params: {
        complete: this.queryCache.complete,
        id:this.queryCache.id,
      },
    });
  };

  handleDetail = (id,e) => {
    e.preventDefault();
    location.replace("/#/orderItem?id="+id);
}

  editItem = (record, e) => {
    e.preventDefault();
    // TODO: record 为该行所对应的数据，可自定义操作行为
  };

  renderOperations = (value, index, record) => {
    return (
      <div
        className="filter-table-operation"
        style={styles.filterTableOperation}
      >  
        <a href='#' target="_blank" onClick={this.handleDetail.bind(this,record.id)} style={styles.operationItem}>
        订单条目
        </a>
      </div>
    );
  };

  renderStatus = (value) => {
    return (
      value?"完成":"未完成"
    );
  };

  filterFormChange = (value) => {
    this.setState({
      filterFormValue: value,
    });
  };

  filterTable = () => {

    // 合并参数，请求数据
    this.queryCache = {
      ...this.queryCache,
      ...this.state.filterFormValue,
    };
    this.fetchData();
  };

  resetFilter = () => {
    this.setState({
      filterFormValue: {},
    });
  };

  render() {
     const tableData = this.props.bindingData.tableData;
    const { filterFormValue } = this.state;
     /* let result=[];
      //过滤订单编号
      if(filterFormValue.id){
        tableData.list.forEach(d=>{
          if(filterFormValue.id==d.id){
            result.push(d);
          }
        });
      }else{
        result=tableData.list;
      }
      tableData.list=result; */
    return (
      <div className="filter-table">
        <IceContainer title="订单信息">
          <FilterForm
            value={filterFormValue}
            onChange={this.filterFormChange}
            onSubmit={this.filterTable}
            onReset={this.resetFilter}
            fetchData={this.fetchData}
          />
        </IceContainer>
        <IceContainer>
          <Table
            dataSource={tableData.list}
            isLoading={tableData.__loading}
            className="basic-table"
            style={styles.basicTable}
            hasBorder={false}
          >   
          <Table.Column
              title="订单编号"
              dataIndex="orderId"
              width={150}
            />
            <Table.Column
              title="货物名"
              dataIndex="goodsName"
              width={150}
            />
            <Table.Column
              title="货物类型"
              dataIndex="goodsType"
              width={150}
            />
            <Table.Column
              title="货物数量"
              dataIndex="goodsNumber"
              width={150}
            />
            <Table.Column
              title="状态"
              dataIndex="complete"
              width={85}
              cell={this.renderStatus}
            />
            <Table.Column
              title="操作"
              dataIndex="operation"
              width={150}
              cell={this.renderOperations}
            />
          </Table>
        </IceContainer>
      </div>
    );
  }
}

const styles = {
  filterTableOperation: {
    lineHeight: '28px',
  },
  operationItem: {
    marginRight: '12px',
    textDecoration: 'none',
    color: '#5485F7',
  },
  titleWrapper: {
    display: 'flex',
    flexDirection: 'row',
  },
  title: {
    marginLeft: '10px',
    lineHeight: '20px',
  },
  paginationWrapper: {
    textAlign: 'right',
    paddingTop: '26px',
  },
};