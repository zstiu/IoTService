/* eslint react/no-string-refs:0 */
import React, { Component } from 'react';
import { Input, Button, Checkbox, Grid, Feedback, Select } from '@icedesign/base';
import { HashRouter as Router, Route, Link } from 'react-router-dom';
import {
  FormBinderWrapper as IceFormBinderWrapper,
  FormBinder as IceFormBinder,
  FormError as IceFormError,
} from '@icedesign/form-binder';

import { axios } from '../../../../axios'
import IceIcon from '@icedesign/icon';
import './UserLogin.scss';

const { Row, Col } = Grid;

// 寻找背景图片可以从 https://unsplash.com/ 寻找
const backgroundImage =
  'https://img.alicdn.com/tfs/TB1zsNhXTtYBeNjy1XdXXXXyVXa-2252-1500.png';

export default class UserLogin extends Component {
  static displayName = 'UserLogin';

  static propTypes = {};

  static defaultProps = {};

  constructor(props) {
    super(props);
    this.state = {
      value: {
        userName: undefined,
        password: undefined,
        checkbox: false,
        type: 0,
      },
    };
  }

  formChange = (value) => {
    this.setState({
      value,
    });
  };

  handleSubmit = (e) => {
    e.preventDefault();
    this.refs.form.validateAll((errors, values) => {
      if (errors) {
        console.log('errors', errors);
        return;
      }
      console.log('values:', values);
      //Feedback.toast.success('登录成功');
      if(values.type=="2"){
        this.loginManager(values);
      }else{
        this.loginUser(values);
      }
      
      // 登录成功后可通过 hashHistory.push('/') 跳转首页
    });
  };

  loginUser = (params) => {
    axios.post('/user/login', params).then((response) => {
      if(response.data.success){
        console.log(response);
        Feedback.toast.success('登录成功');
        if(params.type=="1"){
          const user = {
            id:response.data.data.id,
            userName:response.data.data.name,
            type:1,
          }
          localStorage.setItem('user',JSON.stringify(user));
          location.replace("#/cars");
        }else{
          const user = {
            id:response.data.data.id,
            userName:response.data.data.name,
            type:0,
          }
          localStorage.setItem('user',JSON.stringify(user));
          location.replace("#/orders");
        }
      }else{
        console.log(response);
        Feedback.toast.error('登录失败：'+response.data.message);
      }
    }).catch(res => {
      console.log(res);
      Feedback.toast.error('登录失败');
    });
  }
  loginManager = (values) => {
    const tempparams = {
      managerName: values.userName,
      password: values.password,
    }
    console.log(tempparams);
    axios.post('/manager/login', tempparams).then((response) => {
      if(response.data.success){
        const user = {
          id:response.data.data.id,
          userName:response.data.data.managerName,
          type:2,
        }
        localStorage.setItem('user',JSON.stringify(user));
        Feedback.toast.success('登录成功');
        location.replace("#/device");
      }else{
        console.log(response);
        Feedback.toast.error('登录失败：'+response.data.message);
      }
    }).catch(res => {
      Feedback.toast.error('登录失败');
    });
  }

  render() {
    return (
      <div style={styles.userLogin} className="user-login">
        <div
          style={{
            ...styles.userLoginBg,
            backgroundImage: `url(${backgroundImage})`,
          }}
        />
        <div style={styles.contentWrapper} className="content-wrapper">
          <h2 style={styles.slogan} className="slogan">
            欢迎使用 <br /> 智能物流系统
          </h2>
          <div style={styles.formContainer}>
            <h4 style={styles.formTitle}>登录</h4>
            <IceFormBinderWrapper
              value={this.state.value}
              onChange={this.formChange}
              ref="form"
            >
              <div style={styles.formItems}>
                <Row style={styles.formItem}>
                  <Col>
                    <IceIcon
                      type="person"
                      size="small"
                      style={styles.inputIcon}
                    />
                    <IceFormBinder name="userName" required message="必填">
                      <Input maxLength={20} placeholder="会员名/邮箱/手机号" />
                    </IceFormBinder>
                  </Col>
                  <Col>
                    <IceFormError name="userName" />
                  </Col>
                </Row>

                <Row style={styles.formItem}>
                  <Col>
                    <IceIcon
                      type="lock"
                      size="small"
                      style={styles.inputIcon}
                    />
                    <IceFormBinder name="password" required message="必填">
                      <Input htmlType="password" placeholder="密码" />
                    </IceFormBinder>
                  </Col>
                  <Col>
                    <IceFormError name="password" />
                  </Col>
                </Row>

                <Row style={styles.formItem}>
                  <Col style={styles.formItemCol}>
                    <IceIcon type="down" size="small" style={styles.inputIcon} />
                    <IceFormBinder
                      name="type"
                    >
                      <Select size="large" style={styles.selectCss} >
                        <Select.Option value="0">货运公司</Select.Option>
                        <Select.Option value="1">运输公司</Select.Option>
                        <Select.Option value="2">管理员</Select.Option>
                      </Select>
                    </IceFormBinder>
                  </Col>
                  <Col>
                    <IceFormError name="type" />
                  </Col>
                </Row>

                <Row style={styles.formItem}>
                  <Col>
                    <IceFormBinder name="checkbox">
                      <Checkbox style={styles.checkbox}>记住账号</Checkbox>
                    </IceFormBinder>
                  </Col>
                </Row>

                <Row style={styles.formItem}>
                  <Button
                    type="primary"
                    onClick={this.handleSubmit}
                    style={styles.submitBtn}
                  >
                    登 录
                  </Button>
                </Row>

                <Row className="tips" style={styles.tips}>
                  <a href="/#/register" style={styles.link}>
                    立即注册
                  </a>
                  <span style={styles.line}>|</span>
                  <a href="/" style={styles.link}>
                    忘记密码
                  </a>
                  <span style={styles.line}>|</span>
                  <a href="/" style={styles.link}>
                    首页
                  </a>
                </Row>
              </div>
            </IceFormBinderWrapper>
          </div>
        </div>
      </div>
    );
  }
}

const styles = {
  userLogin: {
    position: 'relative',
    height: '100vh',
  },
  userLoginBg: {
    position: 'absolute',
    top: 0,
    left: 0,
    right: 0,
    bottom: 0,
    backgroundSize: 'cover',
  },
  formContainer: {
    display: 'flex',
    justifyContent: 'center',
    flexDirection: 'column',
    padding: '30px 40px',
    background: '#fff',
    borderRadius: '6px',
    boxShadow: '1px 1px 2px #eee',
  },
  formItem: {
    position: 'relative',
    marginBottom: '25px',
    flexDirection: 'column',
  },
  formTitle: {
    margin: '0 0 20px',
    textAlign: 'center',
    color: '#3080fe',
    letterSpacing: '12px',
  },
  inputIcon: {
    position: 'absolute',
    left: '0px',
    top: '3px',
    color: '#999',
  },
  submitBtn: {
    width: '240px',
    background: '#3080fe',
    borderRadius: '28px',
  },
  checkbox: {
    marginLeft: '5px',
  },
  tips: {
    textAlign: 'center',
  },
  link: {
    color: '#999',
    textDecoration: 'none',
    fontSize: '13px',
  },
  line: {
    color: '#dcd6d6',
    margin: '0 8px',
  },
  selectCss: {
    width: '246px',
  },
};
