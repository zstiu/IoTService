import React, { Component } from 'react';
import { Input, Grid, Select, Button, DatePicker } from '@icedesign/base';
import SimpleFormDialog from '../../SimpleFormDialog'
// form binder 详细用法请参见官方文档
import {
  FormBinderWrapper as IceFormBinderWrapper,
  FormBinder as IceFormBinder,
} from '@icedesign/form-binder';

const { Row, Col } = Grid;
const { Option } = Select;

export default class Filter extends Component {
  static displayName = 'Filter';
  onAdd = () => {
    this.refs.getDialog.showDialog();
  }

  fetchData = () => {
    this.props.fetchData();
  };
  render() {
    return (
      <IceFormBinderWrapper
        value={this.props.value}
        onChange={this.props.onChange}
      >
        <div>
          <Row wrap>
            <Col xxs={24} xs={12} l={8} style={styles.filterCol}>
              <label style={styles.filterTitle}>订单编号</label>
              <IceFormBinder>
                <Input name="id" />
              </IceFormBinder>
            </Col>
            <Col xxs={24} xs={12} l={8} style={styles.filterCol}>
              <label style={styles.filterTitle}>状态</label>
              <IceFormBinder>
                <Select name="complete" style={styles.filterTool} defaultValue="false">
                  <Option value="false">未完成</Option>
                  <Option value="true" >完成</Option>
                </Select>
              </IceFormBinder>
            </Col>
          </Row>
          <div
            style={{
              textAlign: 'left',
              marginLeft: '12px',
            }}
          >
            <Button
              onClick={this.props.onSubmit}
              type="primary"
              style={{ marginLeft: '10px' }}
            >
              确定
            </Button>

            <Button
              onClick={this.onAdd}
              type="primary"
              style={{ marginLeft: '10px' }}
            >
              下订单
            </Button>
            <SimpleFormDialog ref="getDialog" fetchData={this.fetchData}/>
          </div>
        </div>
      </IceFormBinderWrapper>
    );
  }
}

const styles = {
  filterCol: {
    display: 'flex',
    alignItems: 'center',
    marginBottom: '20px',
  },

  filterTitle: {
    width: '68px',
    textAlign: 'right',
    marginRight: '12px',
    fontSize: '14px',
  },

  filterTool: {
    width: '200px',
  },
};
