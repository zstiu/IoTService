import React, { Component } from 'react';
import PropTypes from 'prop-types';

export default class PlatformIntro2 extends Component {
  static displayName = 'PlatformIntro2';

  static propTypes = {
    value: PropTypes.string,
  };

  static defaultProps = {
    value: 'string data',
  };

  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    return (
      <div
        id="about"
        style={{
          ...styles.wrapper,
          backgroundImage:
            'url(https://img.alicdn.com/tfs/TB1d..oRVXXXXX4XVXXXXXXXXXX-2760-1480.png)',
        }}
      >
        <div style={styles.body}>
          <h2 style={styles.title}>公司简介</h2>
          <p style={styles.text}>
          企业名称：XX箱体公司<br/>统一社会信用代码：XXXXXXXXXXXXXXXXXXX
          <br/>工商注册号：XXXXXXXXXXXXXXX<br/>经营状态：在营（开业）
          <br/>企业机构类型：个人独资企业
          <br/>成立日期：2018-01-01
          <br/>法定代表人：张三
          <br/>经营期限：2018-01-01 ~ 长期
          <br/>注册资本（万元）：280.0000
          <br/>注册资本币种：人民币元
          <br/>登记机关：江苏省XX市XX区XX机关
          <br/>最后年检年度：2018-01-01
          <br/>地址：江苏省XX市XX区XX街道000号
          <br/>经营业务范围：专业打造物流监控箱体，精密仪器存储、货运箱体，箱体设备上可以安装各类传感器，实时对物流状态进行监控。
          </p>
        </div>
        {/* <img
          src="https://img.alicdn.com/tfs/TB1DzIrRVXXXXckXFXXXXXXXXXX-1740-800.png"
          alt=""
          width="870"
          height="400"
          style={styles.image}
        /> */}
      </div>
    );
  }
}

const styles = {
  wrapper: {
    height: 740,
    overflow: 'hidden',
  },
  body: {
    textAlign: 'center',
  },
  title: {
    fontSize: 24,
    color: '#000',
    marginBottom: 20,
    marginTop: 50,
  },
  text: {
    fontSize: 14,
    color: '#666',
    lineHeight: '24px',
    letterSpacing: '2px',
  },
  image: {
    margin: '0 auto',
    marginTop: 50,
    display: 'block',
  },
};
