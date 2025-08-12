import FavoritesCard from '../../components/FavoritesCard';
import { favoritesData } from '../../data/profile';

export default function Favorites() {
  return (
    <div className="max-w-6xl mx-auto p-8">
      <div className="text-center mb-12">
        <h1 className="text-5xl font-bold mb-6 bg-gradient-to-r from-white via-blue-200 to-cyan-200 bg-clip-text text-transparent">
          좋아하는 것
        </h1>
        <p className="text-xl text-gray-300 max-w-2xl mx-auto">
          제가 좋아하는 것들을 통해 저를 더 깊이 이해해보세요
        </p>
      </div>
      
      <div className="grid grid-cols-1 lg:grid-cols-2 gap-8 mb-12">
        <FavoritesCard 
          title="좋아하는 음식"
          items={favoritesData.foods}
          icon="🍕"
        />
        
        <FavoritesCard 
          title="취미"
          items={favoritesData.hobbies}
          icon="🎨"
        />
        
        <FavoritesCard 
          title="좋아하는 색깔"
          items={favoritesData.colors}
          icon="🌈"
          variant="color"
        />
        
        <FavoritesCard 
          title="좋아하는 장소"
          items={favoritesData.places}
          icon="🏠"
        />
      </div>
      
      {/* 특별한 메모 */}
      <div className="bg-gradient-to-r from-blue-500/10 via-cyan-500/10 to-emerald-500/10 backdrop-blur-md rounded-2xl p-8 border border-gray-700/50">
        <h3 className="text-2xl font-bold mb-4 text-white flex items-center">
          <span className="text-3xl mr-3">💭</span>
          특별한 메모
        </h3>
        <p className="text-gray-300 leading-relaxed text-lg">
          새로운 것을 배우는 것을 정말 좋아해요! 특히 새로운 기술이나 트렌드를 
          알아가는 것이 즐겁습니다. 맛있는 음식을 먹으면서 좋은 음악을 듣는 
          시간이 가장 행복한 순간이에요. 🌟
        </p>
        <div className="mt-6 flex flex-wrap gap-3">
          <span className="px-3 py-1 bg-gray-700/50 rounded-full text-sm text-white border border-gray-600/50">#새로운도전</span>
          <span className="px-3 py-1 bg-gray-700/50 rounded-full text-sm text-white border border-gray-600/50">#음악</span>
          <span className="px-3 py-1 bg-gray-700/50 rounded-full text-sm text-white border border-gray-600/50">#맛있는음식</span>
          <span className="px-3 py-1 bg-gray-700/50 rounded-full text-sm text-white border border-gray-600/50">#행복한순간</span>
        </div>
      </div>
    </div>
  );
} 