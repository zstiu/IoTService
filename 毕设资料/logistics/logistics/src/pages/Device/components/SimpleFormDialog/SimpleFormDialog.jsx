import React, { Component } from 'react';
import { HashRouter as Router, Route, Link } from 'react-router-dom';
import { Form,Dialog, Grid, Input, Radio, Button,Field,Feedback } from '@icedesign/base';
import IceContainer from '@icedesign/container';
import {
  FormBinderWrapper as IceFormBinderWrapper,
  FormBinder as IceFormBinder,
  FormError as IceFormError,
} from '@icedesign/form-binder';
import { enquireScreen } from 'enquire-js';
import {axios} from '../../../../axios'
const { Row, Col } = Grid;
const { Group: RadioGroup } = Radio;

const defaultValue = {
};

export default class SimpleFormDialog extends Component {
  static displayName = 'SimpleFormDialog';

  constructor(props) {
    super(props);
    this.state = {
      visible: false,
      value: defaultValue,
      title:true,
      isMobile: false,
      orderId:this.props.orderId,
    };
  }

  componentDidMount() {
    this.enquireScreenRegister();
  }


  fetchData(){
    this.props.fetchData();
  }

  enquireScreenRegister = () => {
    const mediaCondition = 'only screen and (max-width: 720px)';

    enquireScreen((mobile) => {
      this.setState({
        isMobile: mobile,
      });
    }, mediaCondition);
  };

  showDialog = () => {
    this.setState({
      visible: true,
      title: true,
      value: {},
    });
  };


  edit = (record) => {
    this.setState({
      visible: true,
      title:false,
      value:record,
    });
  };


  hideDialog = () => {
    this.setState({
      visible: false,
      title: true,
      value: {},
    });
  };

  onOk = () => {
    this.refForm.validateAll((error,values) => {
      if (error) {
        // show validate error
        return;
      }
      // deal with value
      if(this.state.title){
        const userStr = localStorage.getItem('user');
        const user = JSON.parse(userStr);
          let newValue={
            "goodsName":values.goodsName,
            "goodsType":values.goodsType,
            "goodsNumber":values.goodsNumber,
            "userId":user.id,
          }
          console.log(newValue);
          axios.post('/order/',newValue).then((response) => {
            if(response.data.success){
              Feedback.toast.success('下单成功');
              location.replace("/#/orders");
            }else{
              Feedback.toast.error('下单失败');
            }
         
          this.fetchData();
          // 注册成功后做对应的逻辑处理
        }).catch(res => {
          Feedback.toast.error('添加失败');
      });
      }
      
      this.hideDialog();
    });
  };

  onFormChange = (value) => {
    this.setState({
      value,
    });
  };

  render() {
    const { isMobile } = this.state;
    const simpleFormDialog = {
      ...styles.simpleFormDialog,
    };
    // 响应式处理
    if (isMobile) {
      simpleFormDialog.width = '300px';
    }

    return (
      <IceContainer>
        <Dialog
          className="simple-form-dialog"
          style={simpleFormDialog}
          autoFocus={false}
          footerAlign="center"
          title={this.state.title?"下订单":"编辑订单项"}
          {...this.props}
          onOk={this.onOk}
          onCancel={this.hideDialog}
          onClose={this.hideDialog}
          isFullScreen
          visible={this.state.visible}
        >
          <IceFormBinderWrapper
            ref={(ref) => {
              this.refForm = ref;
            }}
            value={this.state.value}
            onChange={this.onFormChange}
          >
            <div style={styles.dialogContent}>
              <Row style={styles.formRow}>
                <Col span={`${isMobile ? '6' : '5'}`}>
                  <label style={styles.formLabel}>货物名</label>
                </Col>
                <Col span={`${isMobile ? '18' : '16'}`}>
                  <IceFormBinder
                    required
                  >
                    <Input
                      name="goodsName"
                      style={styles.input}
                    />
                  </IceFormBinder>
                  <IceFormError name="goodsName" />
                </Col>
              </Row>

              <Row style={styles.formRow}>
                <Col span={`${isMobile ? '6' : '5'}`}>
                  <label style={styles.formLabel}>货物类型</label>
                </Col>
                <Col span={`${isMobile ? '18' : '16'}`}>
                  <IceFormBinder
                  >
                    <Input
                      name="goodsType"
                      style={styles.input}
                    />
                  </IceFormBinder>
                  <IceFormError name="goodsType" />
                </Col>
              </Row>
              <Row style={styles.formRow}>
                <Col span={`${isMobile ? '6' : '5'}`}>
                  <label style={styles.formLabel}>货物数量</label>
                </Col>
                <Col span={`${isMobile ? '18' : '16'}`}>
                  <IceFormBinder
                    required
                    message="必填"
                  >
                    <Input
                      name="goodsNumber"
                      style={styles.input}
                    />
                  </IceFormBinder>
                  <IceFormError name="goodsNumber" />
                </Col>
              </Row>
            </div>
          </IceFormBinderWrapper>      
        </Dialog>
       
      </IceContainer>
    );
  }
}

const styles = {
  simpleFormDialog: { width: '640px' },
  dialogContent: {},
  formRow: { marginTop: 20 },
  input: { width: '100%' },
  formLabel: { lineHeight: '26px' },
};
