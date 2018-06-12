/* eslint no-underscore-dangle: 0 */
import React, { Component } from 'react';
import { Table, Pagination, Button } from '@icedesign/base';
import IceContainer from '@icedesign/container';
import DataBinder from '@icedesign/data-binder';
import IceLabel from '@icedesign/label';
import FilterForm from './Filter';
import SimpleFormDialog from '../SimpleFormDialog'
import {withRouter} from "react-router-dom";
import { axios } from '../../../../axios';
import queryString from 'query-string';
@withRouter
export default class EnhanceTable extends Component {
  static displayName = 'EnhanceTable';

  static defaultProps = {};

  constructor(props) {
    super(props);
    let search = queryString.parse(this.props.location.search);
    this.state = {
      dataSource: [],
      loading: true,
      search:search,
    };
  }

  componentDidMount() {
    this.fetchData();
  }

  fetchData = () => {
    this.setState({
      loading: true,
    });
    let url = 'http://115.159.26.94:8080/order/'+this.state.search.id+'/orderItem';
    axios(url).then((response) => {
      this.setState({
        dataSource: response.data.data,
        loading: false,
      });
    });
  }



  onAdd = () => {
    this.refs.getDialog.showDialog();
  }

  render() {

    return (
      <div className="filter-table">
        <IceContainer title="订单条目">
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
          <SimpleFormDialog ref="getDialog" fetchData={this.fetchData} orderId={this.state.search.id}/>
        </IceContainer>
        <IceContainer>
          <Table
            dataSource={this.state.dataSource}
            isLoading={this.state.loading}
            className="basic-table"
            style={styles.basicTable}
            hasBorder={false}
          >

            <Table.Column title="箱体设备编号" dataIndex="deviceAuthInfo" width={100} />

            <Table.Column
              title="货物编号"
              dataIndex="goodsNumbering"
              width={100}
            />
            <Table.Column
              title="车辆ID"
              dataIndex="carId"
              width={100}
            />
            <Table.Column
              title="订单ID"
              dataIndex="orderId"
              width={100}
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
