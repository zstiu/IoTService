import React, { Component } from 'react';
import xiangti from'./img/xiangti.png'
export default class PlatformBlackIntro extends Component {
  static displayName = 'PlatformBlackIntro';

  static propTypes = {};

  static defaultProps = {};

  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    return (
      <div
      id="introduce"
        style={{
          ...styles.wrapper,
          backgroundImage:
            'url(https://img.alicdn.com/tfs/TB1.IQmRVXXXXbYXVXXXXXXXXXX-2760-1480.png)',
        }}
      >
        <div style={styles.body}>
          <h2 style={styles.title}>箱体介绍</h2>
         {/*  <p style={styles.text}>
            商品推广佣金，精准转化内容影响力<br />优质内容奖励
            ，为优质内容创作者保驾护航<br />阿里V任务，为你的内容创作能力对接更多潜在客户
          </p> */}
        </div>
        <div style={styles.extraBody}>
          <img
            alt=""
            src={xiangti}
            height="218"
            width="706"
            style={styles.image}
          />
          <div style={styles.extraText}>
            <p style={styles.extraTextItemLeft}>型号：A-001 长：140cm 宽：80cm 高：80cm </p>
            <p style={styles.extraTextItemCenter}>型号：B-001 长：100cm 宽：80cm  高：50cm</p>
            <p style={styles.extraTextItemRight}>型号：C-001 长：120cm 宽：80cm  高：50cm</p>
          </div>
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
    overflow: 'hidden',
  },
  body: {
    textAlign: 'center',
  },
  title: {
    color: '#fff',
    fontSize: 24,
    marginBottom: 20,
    marginTop: 130,
  },
  text: {
    color: '#fff',
    fontSize: 14,
    lineHeight: '24px',
    letterSpacing: '2px',
  },
  extraBody: {
    textAlign: 'center',
    position: 'relative',
    marginTop: 80,
  },
  image: {
    display: 'block',
    margin: '0 auto',
  },
  extraText: {
    width: 706,
    margin: '0 auto',
    display: 'flex',
    color: '#fff',
  },
  extraTextItemLeft: {
    width: '215px',
    textAlign: 'center',
  },
  extraTextItemCenter: {
    width: '275px',
    textAlign: 'center',
  },
  extraTextItemRight: {
    width: '215px',
    textAlign: 'center',
  },
};
