import React, { Component } from 'react';
import PropTypes from 'prop-types';

export default class PlatformIntro extends Component {
  static displayName = 'PlatformIntro';

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
      <div style={{
        ...styles.wrapper,
        backgroundImage:
          'url(https://img.alicdn.com/tfs/TB1Iw2ZRVXXXXb4aFXXXXXXXXXX-2760-1544.png)',
      }}  id="bussiness">
        <div style={styles.body}>
          <h2 style={styles.title}>业务概述</h2>
          <p style={styles.text}>
          货物公司想要运送一批精密仪器，联系到箱体公司，箱体公司根据货物的信息（例如长、宽、高、数量等）选择使用何种型号的箱体即其数量，<br/>
          如果能够满足货物公司的需要则将箱体运送给货物公司，货物公司生成运输订单，并且将仪器与箱体一对一装好，将箱体编号和货物编号对应填表。<br/>
          货物公司联系运输公司运输，运输公司登记好自己信息，再添加用于运输的车辆信息（车牌号和司机电话必须有），此时再将车牌号和所运输的货<br/>
          物编号对应。运输过程中，箱体长安其便开始工作向云端传送数据，服务器再从云端获得数据，根据箱体公司、货物公司所给的标准及时发布报警信息给客户端。<br/>
        箱体公司的箱体上有各类传感器，每隔一定时间会向云端传送各类传感器获得数据，服务器从云端获得数据。<br/>
          箱体公司监控箱体是因为他们需要保证箱体的安全，因为运送货物的箱体也是定制的造价昂贵；货物公司用户监控箱体数据可以获得货物在运输过程中的状态信息<br/>
        （箱体的状态相当于货物的状态，两个环境是一样的）；运输公司不需要监控箱体和货物的状态，但是他们必须要通过客户端获得报警信息，知道自己公司参与运输<br/>
         的车辆信息和运输车辆上的货物编号即什么车运送哪些货物。<br/>
          </p>
        </div>
        {/* <img
          alt=""
          src="https://img.alicdn.com/tfs/TB1kqzXqL1TBuNjy0FjXXajyXXa-2520-1040.jpg"
          width="1260"
          height="520"
          style={styles.image}
        /> */}
      </div>
    );
  }
}

const styles = {
  wrapper: {
    height: 740,
    backgroundColor: '#fff',
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
    letterSpacing: 2,
  },
  image: {
    margin: '20px auto 0 auto',
    display: 'block',
  },
};
