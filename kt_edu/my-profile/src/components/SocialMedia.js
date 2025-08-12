import { socialLinks } from '../data/profile';

export default function SocialMedia({ 
  variant = 'default', 
  links = socialLinks,
  className = ''
}) {
  if (variant === 'compact') {
    return (
      <div className={`flex space-x-4 ${className}`}>
        {links.map((social, index) => (
          <a 
            key={index}
            href={social.url} 
            className={`w-10 h-10 bg-gradient-to-r ${social.color} rounded-lg flex items-center justify-center text-white hover:scale-110 transition-transform`}
            title={social.name}
          >
            <span className="text-lg">{social.icon}</span>
          </a>
        ))}
      </div>
    );
  }

  if (variant === 'cards') {
    return (
      <div className={`grid grid-cols-2 md:grid-cols-4 gap-4 ${className}`}>
        {links.map((social, index) => (
          <a 
            key={index}
            href={social.url} 
            className={`group flex flex-col items-center p-6 bg-gradient-to-br ${social.color.replace('to-', 'to-').replace('from-', 'from-')}/20 rounded-xl border border-${social.color.split('-')[1]}-500/30 hover:border-${social.color.split('-')[1]}-400/50 transition-all duration-300 hover:scale-105`}
          >
            <span className="text-3xl mb-3">{social.icon}</span>
            <span className="text-white font-medium">{social.name}</span>
            <span className="text-gray-400 text-sm">{social.username}</span>
          </a>
        ))}
      </div>
    );
  }

  // 기본 버전
  return (
    <div className={`bg-gray-800/50 backdrop-blur-md rounded-2xl p-8 border border-gray-700/50 ${className}`}>
      <h2 className="text-2xl font-bold mb-8 text-white text-center flex items-center justify-center">
        <span className="text-3xl mr-3">🌐</span>
        소셜 미디어
      </h2>
      <div className="grid grid-cols-2 md:grid-cols-4 gap-4">
        {links.map((social, index) => (
          <a 
            key={index}
            href={social.url} 
            className={`group flex flex-col items-center p-6 bg-gradient-to-br ${social.color.replace('to-', 'to-').replace('from-', 'from-')}/20 rounded-xl border border-${social.color.split('-')[1]}-500/30 hover:border-${social.color.split('-')[1]}-400/50 transition-all duration-300 hover:scale-105`}
          >
            <span className="text-3xl mb-3">{social.icon}</span>
            <span className="text-white font-medium">{social.name}</span>
            <span className="text-gray-400 text-sm">{social.username}</span>
          </a>
        ))}
      </div>
    </div>
  );
} 