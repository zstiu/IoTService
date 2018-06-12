import React, { Component } from 'react';
import Footer from '../../components/Footer';
import Header from '../../components/Header';
import PlatformIntro from './components/PlatformIntro';
import PlatformToolsIntro from './components/PlatformToolsIntro';
import PlatformJoinus from './components/PlatformJoinus';
import PlatformLanding from './components/PlatformLanding';
import PlatformBlackIntro from './components/PlatformBlackIntro';

export default class Home extends Component {
  static displayName = 'Home';

  constructor(props) {
    super(props);
    this.state = {};
  }
  scrollToAnchor = (anchorName) => {
    if (anchorName) {
        // 找到锚点
        let anchorElement = document.getElementById(anchorName);
        // 如果对应id的锚点存在，就跳转到锚点
        if(anchorElement) { anchorElement.scrollIntoView({block: 'start', behavior: 'smooth'}); }
    }
  }

  render() {
    return (
      <div className="home-page" style={{ background: '#fff' }}>
        <Header/>
        <PlatformLanding/>
        <PlatformToolsIntro />
        <PlatformIntro />
        <PlatformBlackIntro />
        <PlatformJoinus />
        <Footer />
      </div>
    );
  }
}
