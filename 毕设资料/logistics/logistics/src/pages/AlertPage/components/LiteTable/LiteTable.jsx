import React, { Component } from 'react';
import IceContainer from '@icedesign/container';
import { Table } from '@icedesign/base';

const styles = {
  processing: {
    color: '#5485F7',
  },
  finish: {
    color: '#64D874',
  },
  terminated: {
    color: '#999999',
  },
  pass: {
    color: '#FA7070',
  },
};

const generatorMockStatus = () => {
  const random = parseInt(Math.random() * 10, 10);
  if (random < 3) {
    return 'processing';
  } else if (random >= 3 && random < 6) {
    return 'finish';
  } else if (random >= 6 && random < 8) {
    return 'terminated';
  } else if (random >= 8) {
    return 'pass';
  }
};

const generatorData = () => {
  return Array.from({ length: 10 }).map((item, index) => {
    return {
      project: `${index}`,
      goodsID: `XT${index}`,
      orderId: `${index}`,
      carNo: '陕A12345',
      tele: '1085971279',
      status: '温度过高',
    };
  });
};

const statusComponents = {
  processing: <span style={styles.processing}>进行中</span>,
  finish: <span style={styles.finish}>已完成</span>,
  terminated: <span style={styles.terminated}>已终止</span>,
  pass: <span style={styles.pass}>未通过</span>,
};

export default class LiteTable extends Component {
  static displayName = 'LiteTable';

  static propTypes = {};

  static defaultProps = {};

  constructor(props) {
    super(props);
    this.state = {
      tableData: generatorData(),
    };
  }

  renderStatus = (value) => {
    return statusComponents[value];
  };

  render() {
    const { tableData } = this.state;
    return (
      <div className="lite-table">
        <IceContainer style={styles.tableCard}>
          <Table dataSource={tableData} hasBorder={false}>
            <Table.Column title="箱体设备编号" dataIndex="project" width={200} />
            <Table.Column title="货物编号" dataIndex="goodsID" width={100} />
            <Table.Column title="订单编号" dataIndex="orderId" width={100} />
            <Table.Column title="车牌号" dataIndex="carNo" width={100} />
            <Table.Column title="司机电话" dataIndex="tele" width={200} />
            <Table.Column
              title="报警原因"
              dataIndex="status"
              width={200}
            />
          </Table>
        </IceContainer>
      </div>
    );
  }
}
