/* eslint no-underscore-dangle: 0 */
import React, { Component } from 'react';
import { Table, Pagination,Button } from '@icedesign/base';
import IceContainer from '@icedesign/container';
import DataBinder from '@icedesign/data-binder';
import IceLabel from '@icedesign/label';
import FilterForm from './Filter';
import SimpleFormDialog from '../SimpleFormDialog'

@DataBinder({
  tableData: {
    // 详细请求配置请参见 https://github.com/axios/axios
    url: 'http://115.159.26.94:8080/car/',
    params: {
     /*  page: 1, */
    },
    defaultBindingData: {
       lists: [], 
      //  pageSize: 10,
      // currentPage: 1, 
    },
     responseFormatter: (responseHandler, res, originResponse) => {
      // 拿到接口返回的 res 数据，做一些格式转换处理，使其符合 DataBinder 的要求
      // 最后再按照顺序丢到 responseHandler 方法里继续执行
        res = {
        success: res.success,
        message: res.message,
        data: {
          lists:res.data
        },
      };
      console.log(res);
      responseHandler(res, originResponse);
    }, 
  },
})
export default class EnhanceTable extends Component {
  static displayName = 'EnhanceTable';

  static defaultProps = {};

  constructor(props) {
    super(props);
  }

  componentDidMount() {
    this.fetchData();
    console.log(this.props.bindingData.tableData);
  }

  fetchData = () => {
    this.props.updateBindingData('tableData');
  };

  editItem = (record, e) => {
    e.preventDefault();
    this.refs.getDialog.edit(record);
    // TODO: record 为该行所对应的数据，可自定义操作行为
  };

  renderOperations = (value, index, record) => {
    return (
      <div
        className="filter-table-operation"
        style={styles.filterTableOperation}
      >
        <a
          href="#"
          style={styles.operationItem}
          target="_blank"
          onClick={this.editItem.bind(this, record)}
        >
          编辑
        </a>
      </div>
    );
  };


  changePage = (currentPage) => {
    this.queryCache.page = currentPage;

    this.fetchData();
  };


  filterTable = () => {
    // 合并参数，请求数据
    this.queryCache = {
      ...this.queryCache,
    };
    this.fetchData();
  };


  onAdd = ()=>{
    this.refs.getDialog.showDialog();
  }

  render() {
    const tableData = this.props.bindingData.tableData;

    return (
      <div className="filter-table">
        <IceContainer title="车辆信息">
        <div
            style={{
              textAlign: 'right',
              marginLeft: '12px',
            }}
          >
            <Button
              onClick={this.onAdd}
              type="primary"
              style={{ marginLeft: '10px' }}
            >
              新增
            </Button>
          </div>
          <SimpleFormDialog ref="getDialog" fetchData={this.fetchData}/>
        </IceContainer>
        <IceContainer>
          <Table
            dataSource={tableData.lists}
            isLoading={tableData.__loading}
            className="basic-table"
            style={styles.basicTable}
            hasBorder={false}
          >
            <Table.Column
              title="司机姓名"
              dataIndex="driverName"
              width={220}
            />
            <Table.Column title="电话" dataIndex="phone" width={100} />
            
            <Table.Column
              title="车辆编号"
              dataIndex="carNumber"
              width={100}
            />
            <Table.Column
              title="操作"
              dataIndex="operation"
              width={150}
              cell={this.renderOperations}
            />
          </Table>
       {/*    <div style={styles.paginationWrapper}>
            <Pagination
              current={tableData.currentPage}
              pageSize={tableData.pageSize}
              total={tableData.total}
              onChange={this.changePage}
            />
          </div> */}
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
