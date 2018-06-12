import React, { Component } from 'react';
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
      value: defaultValue,
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
    });
  };

  onOk = () => {
    this.refForm.validateAll((error,values) => {
      if (error) {
        // show validate error
        return;
      }
      console.log(values);
      // deal with value
      if(this.state.title){
        const userStr = localStorage.getItem('user');
        const user = JSON.parse(userStr);
          let newValue={
            "userId":user.id,
            "carNumber":values.carNumber,
            "driverName":values.driverName,
            "phone":values.phone,
          }
          axios.post('/car/',newValue).then((response) => {
          Feedback.toast.success('添加成功');
          this.fetchData();
          // 注册成功后做对应的逻辑处理
        }).catch(res => {
          Feedback.toast.error('添加失败');
      });
      }else{
        axios.put('/car/',values).then((response) => {
          Feedback.toast.success('编辑成功');
          this.fetchData();
          // 注册成功后做对应的逻辑处理
        }).catch(res => {
          Feedback.toast.error('编辑失败');
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
          title={this.state.title?"添加车辆":"编辑车辆"}
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
                <Col span={`${isMobile ? '6' : '3'}`}>
                  <label style={styles.formLabel}>司机姓名</label>
                </Col>
                <Col span={`${isMobile ? '18' : '16'}`}>
                  <IceFormBinder
                    required
                    min={1}
                    max={10}
                    message="当前字段必填，且最少 1 个字最多 10 个字"
                  >
                    <Input
                      name="driverName"
                      style={styles.input}
                    />
                  </IceFormBinder>
                  <IceFormError name="driverName" />
                </Col>
              </Row>

              <Row style={styles.formRow}>
                <Col span={`${isMobile ? '6' : '3'}`}>
                  <label style={styles.formLabel}>联系电话</label>
                </Col>
                <Col span={`${isMobile ? '18' : '16'}`}>
                  <IceFormBinder
                    required
                    min={11}
                    max={11}
                    message="必须11位"
                  >
                    <Input
                      name="phone"
                      style={styles.input}
                    />
                  </IceFormBinder>
                  <IceFormError name="phone" />
                </Col>
              </Row>
              <Row style={styles.formRow}>
                <Col span={`${isMobile ? '6' : '3'}`}>
                  <label style={styles.formLabel}>车辆编号</label>
                </Col>
                <Col span={`${isMobile ? '18' : '16'}`}>
                  <IceFormBinder
                    required
                    message="必填"
                  >
                    <Input
                      name="carNumber"
                      style={styles.input}
                    />
                  </IceFormBinder>
                  <IceFormError name="carNumber" />
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
