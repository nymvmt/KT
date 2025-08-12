import Link from 'next/link';
import { profileData, navigationLinks } from '../data/profile';

export default function Header({ 
  profile = profileData,
  links = navigationLinks,
  className = ''
}) {
  return (
    <header className={`fixed top-0 left-0 right-0 z-50 bg-black/20 backdrop-blur-md border-b border-gray-700/30 ${className}`}>
      <div className="max-w-6xl mx-auto px-8 py-4">
        <div className="flex items-center justify-between">
          {/* 로고 */}
          <Link href="/" className="flex items-center space-x-3 group">
            <div className="w-10 h-10 bg-gradient-to-r from-blue-500 to-cyan-500 rounded-full flex items-center justify-center text-white font-bold text-lg shadow-lg group-hover:shadow-xl transition-all duration-300">
              {profile.name.charAt(0)}
            </div>
            <div>
              <h1 className="text-xl font-bold bg-gradient-to-r from-white to-blue-200 bg-clip-text text-transparent">
                {profile.name}
              </h1>
              <p className="text-xs text-gray-400">{profile.role}</p>
            </div>
          </Link>

          {/* 네비게이션 */}
          <nav className="hidden md:flex items-center space-x-8">
            {links.map((link, index) => (
              <Link 
                key={index}
                href={link.href} 
                className="text-gray-300 hover:text-white transition-colors duration-300 font-medium"
              >
                {link.name}
              </Link>
            ))}
          </nav>

          {/* 모바일 메뉴 버튼 */}
          <button className="md:hidden p-2 text-gray-300 hover:text-white transition-colors">
            <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M4 6h16M4 12h16M4 18h16" />
            </svg>
          </button>
        </div>
      </div>
    </header>
  );
} 