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
    let url = '/datastream/?device_id='+this.state.search.id;
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
        <IceContainer title="数据流">
          <div
            style={{
              textAlign: 'right',
              marginLeft: '12px',
            }}
          >
            {/* <Button
              onClick={this.onAdd}
              type="primary"
              style={{ marginLeft: '10px' }}
            >
              新增
            </Button> */}
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

            <Table.Column title="状态" dataIndex="id" width={100} />

          {/*   <Table.Column
              title="tags"
              dataIndex="tags"
              width={100}
            />
            <Table.Column
              title="unit"
              dataIndex="unit"
              width={100}
            />
            <Table.Column
              title="unit_symbol"
              dataIndex="unit_symbol"
              width={100}
            />
 */}
            <Table.Column
              title="值"
              dataIndex="current_value"
              width={100}
            />
           {/*  <Table.Column
              title="uuid"
              dataIndex="uuid"
              width={200}
            /> */}
            <Table.Column
              title="更新时间"
              dataIndex="update_at"
              width={130}
            />
            <Table.Column
              title="箱体设备编号"
              dataIndex="device_id"
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
