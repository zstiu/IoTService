import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { Button } from '@icedesign/base';
import wechart from './img/图片1.png'
export default class PlatformJoinUs extends Component {
  static displayName = 'PlatformJoinUs';

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
      <div id="contact"
        style={{
          ...styles.wrapper,
          backgroundImage:
            'url(https://img.alicdn.com/tfs/TB1Iw2ZRVXXXXb4aFXXXXXXXXXX-2760-1544.png)',
        }}
      >
        <div>
          <div style={styles.titleWrapper}>
            <h2 style={styles.title}>联系我们</h2>
            <p>
            公司电话：XXXXXXXXXXX<br/>
            公司邮箱：1085971279@qq.com<br/>
            传真：XXXXXXXXXX<br/>
            客服QQ:1085971279<br/>
            客服微信号：kefuweixin<br/>
            <img
          src={wechart}
          alt=""
          width="350"
          height="350"
          style={styles.image}
        /> 

            </p>
          </div>
         {/*  <div style={styles.buttons}>
            <Button
              style={styles.secondaryButton}
              type="normal"
              component="a"
              href="/#/register"
            >
              注册
            </Button>
            <Button
              style={styles.primaryButton}
              type="primary"
              component="a"
              href="/#/login"
            >
              登录
            </Button>
          </div> */}
        </div>
      </div>
    );
  }
}

const styles = {
  wrapper: {
    height: 740,
    backgroundPosition: 'center',
    backgroundRepeat: 'no-repeat',
    backgroundSize: 'cover',
  },
  titleWrapper: {
    textAlign: 'center',
    paddingTop: 200,
  },
  title: {
    fontSize: 32,
    color: '#333',
    letterSpacing: '2px',
    lineHeight: '48px',
    textAlign: 'center',
  },
  buttons: { textAlign: 'center', marginTop: '60px' },
  primaryButton: {
    height: 50,
    fontSize: 16,
    padding: '0 58px',
    lineHeight: '50px',
    color: '#fff',
  },
  secondaryButton: {
    height: 50,
    fontSize: 16,
    padding: '0 58px',
    lineHeight: '50px',
    marginRight: 20,
    backgroundColor: 'transparent',
    borderColor: '#5485f7',
    color: '#5485f7',
  },
};
