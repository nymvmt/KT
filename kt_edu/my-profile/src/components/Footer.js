import { profileData, navigationLinks, socialLinks } from '../data/profile';

export default function Footer({ 
  profile = profileData,
  links = navigationLinks,
  social = socialLinks,
  className = ''
}) {
  return (
    <footer className={`bg-black/20 backdrop-blur-md border-t border-gray-700/30 mt-20 ${className}`}>
      <div className="max-w-6xl mx-auto px-8 py-12">
        <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
          {/* 프로필 정보 */}
          <div>
            <div className="flex items-center space-x-3 mb-4">
              <div className="w-12 h-12 bg-gradient-to-r from-blue-500 to-cyan-500 rounded-full flex items-center justify-center text-white font-bold text-lg">
                {profile.name.charAt(0)}
              </div>
              <div>
                <h3 className="text-lg font-bold text-white">{profile.name}</h3>
                <p className="text-sm text-gray-400">{profile.role}</p>
              </div>
            </div>
            <p className="text-gray-300 text-sm leading-relaxed">
              {profile.description}
            </p>
          </div>

          {/* 빠른 링크 */}
          <div>
            <h4 className="text-white font-semibold mb-4">빠른 링크</h4>
            <ul className="space-y-2">
              {links.map((link, index) => (
                <li key={index}>
                  <a href={link.href} className="text-gray-400 hover:text-white transition-colors text-sm">
                    {link.name}
                  </a>
                </li>
              ))}
            </ul>
          </div>

          {/* 소셜 미디어 */}
          <div>
            <h4 className="text-white font-semibold mb-4">소셜 미디어</h4>
            <div className="flex space-x-4">
              {social.map((socialItem, index) => (
                <a 
                  key={index}
                  href={socialItem.url} 
                  className={`w-10 h-10 bg-gradient-to-r ${socialItem.color} rounded-lg flex items-center justify-center text-white hover:scale-110 transition-transform`}
                  title={socialItem.name}
                >
                  <span className="text-lg">{socialItem.icon}</span>
                </a>
              ))}
            </div>
          </div>
        </div>

        {/* 구분선 */}
        <div className="border-t border-gray-700/30 mt-8 pt-8">
          <div className="flex flex-col md:flex-row justify-between items-center">
            <p className="text-gray-400 text-sm">
              © 2024 {profile.name}. All rights reserved.
            </p>
            <div className="flex items-center space-x-4 mt-4 md:mt-0">
              <div className="flex items-center space-x-2 text-sm text-gray-400">
                <div className="w-2 h-2 bg-green-400 rounded-full animate-pulse"></div>
                <span>{profile.status}</span>
              </div>
              <span className="text-gray-600">•</span>
              <span className="text-gray-400 text-sm">{profile.location}</span>
            </div>
          </div>
        </div>
      </div>
    </footer>
  );
} 