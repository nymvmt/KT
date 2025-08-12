import ProfileCard from '../../components/ProfileCard';
import SkillsCard from '../../components/SkillsCard';
import { profileData, skillsData } from '../../data/profile';

export default function About() {
  return (
    <div className="max-w-6xl mx-auto p-8">
      <div className="text-center mb-12">
        <h1 className="text-5xl font-bold mb-6 bg-gradient-to-r from-white via-blue-200 to-cyan-200 bg-clip-text text-transparent">
          소개
        </h1>
      </div>
      
      <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
        {/* 프로필 카드 */}
        <div className="lg:col-span-1">
          <ProfileCard />
        </div>
        
        {/* 메인 컨텐츠 */}
        <div className="lg:col-span-2 space-y-8">
          {/* 인사말 */}
          <div className="bg-gray-800/50 backdrop-blur-md rounded-2xl p-8 border border-gray-700/50">
            <h3 className="text-2xl font-bold mb-4 text-white flex items-center">
              <span className="text-3xl mr-3">👋</span>
              안녕하세요!
            </h3>
            <p className="text-gray-300 leading-relaxed text-lg">
              저는 IT 컨설턴트로 일하고 있는 김나영입니다. 
              새로운 기술을 배우는 것을 좋아하고, 창의적인 솔루션을 찾아내는 것에 열정을 가지고 있습니다.
              사용자 중심의 혁신적인 IT 솔루션을 제공하여 더 나은 디지털 경험을 만들어가는 것이 저의 목표입니다.
            </p>
          </div>
          
          {/* 미션 & 비전 */}
          <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
            <div className="bg-gradient-to-br from-blue-500/20 to-cyan-500/20 backdrop-blur-md rounded-2xl p-6 border border-blue-500/30">
              <h3 className="text-xl font-bold mb-3 text-white flex items-center">
                <span className="text-2xl mr-2">🎯</span>
                미션
              </h3>
              <p className="text-gray-300 leading-relaxed">
                사용자 중심의 창의적 솔루션으로 세상을 더 나은 곳으로 만들기
              </p>
            </div>
            <div className="bg-gradient-to-br from-emerald-500/20 to-teal-500/20 backdrop-blur-md rounded-2xl p-6 border border-emerald-500/30">
              <h3 className="text-xl font-bold mb-3 text-white flex items-center">
                <span className="text-2xl mr-2">💡</span>
                철학
              </h3>
              <p className="text-gray-300 leading-relaxed">
                창의성과 논리의 조화로 혁신적인 경험 설계
              </p>
            </div>
          </div>
          
          {/* 전문 분야 */}
          <SkillsCard 
            title="전문 분야"
            skills={skillsData}
            icon="⚡"
          />
        </div>
      </div>
    </div>
  );
} 