import { profileData, contactInfo } from '../data/profile';

export default function ProfileCard({ 
  variant = 'default', 
  profile = profileData,
  contact = contactInfo,
  className = ''
}) {
  if (variant === 'compact') {
    return (
      <div className={`bg-gray-800/50 backdrop-blur-md rounded-2xl p-6 border border-gray-700/50 ${className}`}>
        <div className="text-center">
          <div className="w-20 h-20 mx-auto mb-4 bg-gradient-to-r from-blue-500 to-cyan-500 rounded-full flex items-center justify-center text-2xl font-bold text-white shadow-lg">
            {profile.name.charAt(0)}
          </div>
          <h3 className="text-xl font-bold text-white mb-1">{profile.name}</h3>
          <p className="text-blue-300 font-medium mb-3">{profile.role}</p>
          <div className="flex items-center justify-center space-x-4 text-sm text-gray-400">
            <div className="flex items-center">
              <div className="w-2 h-2 bg-green-400 rounded-full mr-2 animate-pulse"></div>
              <span>{profile.status}</span>
            </div>
          </div>
        </div>
      </div>
    );
  }

  if (variant === 'contact') {
    return (
      <div className={`bg-gray-800/50 backdrop-blur-md rounded-2xl p-8 border border-gray-700/50 ${className}`}>
        <h2 className="text-2xl font-bold mb-8 text-white flex items-center">
          <span className="text-3xl mr-3">📞</span>
          연락처 정보
        </h2>
        
        <div className="space-y-6">
          {contact.map((item, index) => (
            <div key={index} className="group flex items-center p-6 bg-gray-700/30 rounded-xl border border-gray-600/50 hover:border-gray-500/70 transition-all duration-300 hover:scale-105">
              <div className={`w-12 h-12 bg-gradient-to-r ${item.color} rounded-lg flex items-center justify-center mr-4`}>
                <span className="text-white text-xl">{item.icon}</span>
              </div>
              <div>
                <p className="text-white font-semibold">{item.type}</p>
                <p className="text-gray-300">{item.value}</p>
              </div>
            </div>
          ))}
        </div>
        
        <div className="mt-8 p-6 bg-gradient-to-r from-blue-500/10 to-cyan-500/10 rounded-xl border border-gray-600/50">
          <h3 className="text-lg font-semibold mb-3 text-white flex items-center">
            <span className="text-2xl mr-2">💬</span>
            연락하기 전에
          </h3>
          <p className="text-gray-300 text-sm leading-relaxed">
            궁금한 점이나 협업 제안이 있으시면 언제든 연락주세요! 
            빠른 시일 내에 답변드리도록 하겠습니다. 😊
          </p>
        </div>
      </div>
    );
  }

  // 기본 프로필 카드
  return (
    <div className={`bg-gray-800/50 backdrop-blur-md rounded-2xl p-8 border border-gray-700/50 ${className}`}>
      <div className="text-center mb-8">
        <div className="w-32 h-32 mx-auto mb-6 bg-gradient-to-r from-blue-500 to-cyan-500 rounded-full flex items-center justify-center text-4xl font-bold text-white shadow-2xl">
          {profile.name.charAt(0)}
        </div>
        <h2 className="text-3xl font-bold text-white mb-2">{profile.name}</h2>
        <p className="text-xl text-blue-300 font-medium">{profile.role}</p>
        <div className="mt-4 flex justify-center space-x-4 text-sm text-gray-400">
          <div className="flex items-center">
            <div className="w-2 h-2 bg-green-400 rounded-full mr-2 animate-pulse"></div>
            <span>{profile.status}</span>
          </div>
        </div>
      </div>
      
      <div className="space-y-4">
        <div className="flex items-center p-3 bg-gray-700/30 rounded-lg">
          <span className="text-2xl mr-3">🎓</span>
          <div>
            <p className="text-white font-medium">{profile.education}</p>
            <p className="text-gray-400 text-sm">{profile.major}</p>
          </div>
        </div>
        <div className="flex items-center p-3 bg-gray-700/30 rounded-lg">
          <span className="text-2xl mr-3">💼</span>
          <div>
            <p className="text-white font-medium">IT 컨설팅</p>
            <p className="text-gray-400 text-sm">{profile.experience}</p>
          </div>
        </div>
        <div className="flex items-center p-3 bg-gray-700/30 rounded-lg">
          <span className="text-2xl mr-3">📍</span>
          <div>
            <p className="text-white font-medium">{profile.location}</p>
            <p className="text-gray-400 text-sm">{profile.country}</p>
          </div>
        </div>
      </div>
    </div>
  );
} 