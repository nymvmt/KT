'use client';

import Link from 'next/link';
import { profileData, navigationLinks } from '../data/profile';

export default function Home() {
  return (
    <div className="max-w-6xl mx-auto p-8">
      {/* 헤더 */}
      <div className="text-center mb-16">
        <div className="inline-block p-2 bg-gray-800/50 backdrop-blur-md rounded-full mb-8 border border-gray-700/50">
          <div className="w-24 h-24 bg-gradient-to-r from-blue-500 to-cyan-500 rounded-full flex items-center justify-center text-3xl font-bold text-white shadow-2xl">
            {profileData.name.charAt(0)}
          </div>
        </div>
        
        <h1 className="text-6xl font-bold mb-6 bg-gradient-to-r from-white via-blue-200 to-cyan-200 bg-clip-text text-transparent">
          {profileData.name}의 웹사이트
        </h1>
        
        <p className="text-xl text-gray-300 mb-8 max-w-2xl mx-auto leading-relaxed">
          {profileData.description}
        </p>
        
        <div className="flex items-center justify-center space-x-4 text-sm text-gray-400">
          <div className="flex items-center">
            <div className="w-2 h-2 bg-green-400 rounded-full mr-2 animate-pulse"></div>
            <span>{profileData.status}</span>
          </div>
          <span>•</span>
          <span>{profileData.location}</span>
        </div>
      </div>
      
      {/* 네비게이션 카드들 */}
      <div className="grid grid-cols-1 md:grid-cols-3 gap-8 mb-16">
        {navigationLinks.map((link, index) => {
          const colors = [
            'from-blue-500 to-cyan-500',
            'from-emerald-500 to-teal-500', 
            'from-cyan-500 to-blue-500'
          ];
          const textColors = [
            'text-blue-300 group-hover:text-blue-200',
            'text-emerald-300 group-hover:text-emerald-200',
            'text-cyan-300 group-hover:text-cyan-200'
          ];
          
          return (
            <Link 
              key={index}
              href={link.href} 
              className="group relative h-80"
            >
              <div className={`absolute inset-0 bg-gradient-to-r ${colors[index]} rounded-2xl blur opacity-20 group-hover:opacity-40 transition duration-500`}></div>
              <div className="relative bg-gray-800/50 backdrop-blur-md rounded-2xl p-8 border border-gray-700/50 hover:border-gray-600/70 transition-all duration-300 hover:scale-105 h-full flex flex-col">
                <div className="text-4xl mb-4">{link.icon}</div>
                <h3 className="text-2xl font-bold text-white mb-3">{link.name}</h3>
                <p className="text-gray-300 leading-relaxed flex-grow">
                  {link.name === '소개' && '저에 대해 더 자세히 알아보세요. 경력, 학력, 그리고 목표에 대한 이야기를 담았습니다.'}
                  {link.name === '좋아하는 것' && '제가 좋아하는 음식, 취미, 색깔, 장소들을 소개합니다. 저의 취향을 통해 저를 더 깊이 이해해보세요.'}
                  {link.name === '연락처' && '궁금한 점이나 협업 제안이 있으시면 언제든 연락주세요. 빠른 시일 내에 답변드리겠습니다.'}
                </p>
                <div className={`mt-6 flex items-center ${textColors[index]} transition-colors`}>
                  <span className="mr-2">
                    {link.name === '연락처' ? '연락하기' : '자세히 보기'}
                  </span>
                  <svg className="w-4 h-4 transform group-hover:translate-x-1 transition-transform" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M9 5l7 7-7 7" />
                  </svg>
                </div>
              </div>
            </Link>
          );
        })}
      </div>
      
      {/* 하단 섹션 */}
      <div className="text-center">
        <div className="inline-flex items-center space-x-6 text-gray-400">
          <div className="flex items-center space-x-2">
            <svg className="w-5 h-5" fill="currentColor" viewBox="0 0 20 20">
              <path fillRule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm1-12a1 1 0 10-2 0v4a1 1 0 00.293.707l2.828 2.829a1 1 0 101.415-1.415L11 9.586V6z" clipRule="evenodd" />
            </svg>
            <span>최근 업데이트: 2024년</span>
          </div>
          <div className="flex items-center space-x-2">
            <svg className="w-5 h-5" fill="currentColor" viewBox="0 0 20 20">
              <path fillRule="evenodd" d="M5.05 4.05a7 7 0 119.9 9.9L10 18.9l-4.95-4.95a7 7 0 010-9.9zM10 11a2 2 0 100-4 2 2 0 000 4z" clipRule="evenodd" />
            </svg>
            <span>{profileData.location}</span>
          </div>
        </div>
      </div>
    </div>
  );
}
