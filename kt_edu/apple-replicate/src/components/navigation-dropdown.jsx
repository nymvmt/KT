import Link from "next/link"

export default function NavigationDropdown({ menuData, isVisible }) {
  if (!isVisible || !menuData) return null

  return (
    <div className="absolute top-full left-1/2 transform -translate-x-1/2 mt-2 w-[800px] bg-white rounded-2xl shadow-2xl border border-gray-100 p-8 z-50">
      <div className="grid grid-cols-3 gap-8">
        {Object.entries(menuData).map(([columnTitle, items]) => (
          <div key={columnTitle}>
            <h3 className="text-xs font-medium text-gray-500 mb-4 uppercase tracking-wide">{columnTitle}</h3>
            <ul className="space-y-3">
              {items.map((item, index) => (
                <li key={index}>
                  <Link
                    href={item.href || "#"}
                    className={`block hover:text-blue-600 transition-colors ${
                      item.isLarge
                        ? "text-2xl font-semibold text-black mb-2"
                        : item.isBold
                          ? "text-sm font-semibold text-black"
                          : "text-sm text-gray-700"
                    }`}
                  >
                    {item.title}
                  </Link>
                </li>
              ))}
            </ul>
          </div>
        ))}
      </div>
    </div>
  )
}
